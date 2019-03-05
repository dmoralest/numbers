package com.kklte.numbers.services;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Properties;
import java.util.StringJoiner;
import java.util.TreeMap;

@Service
public class NumberTranslateServiceImpl implements INumberTranslateService {

    private static final String AND = "and";

    /**
     * Bean reference to the number names properties object
     */
    @Autowired
    @Qualifier("numberNames")
    protected Properties numberNames;

    /**
     * TreeMap with natural ordering to hold the entries for the number names
     */
    protected TreeMap<Long, String> treeMapNumberNames;

    /**
     * Sets up the number name's treeMap converting the keys in the properties map to Number values.
     */
    @PostConstruct
    public void setupNumberNamesTreeMap() {
        this.treeMapNumberNames = numberNames.entrySet().stream()
                .collect(TreeMap::new,
                        (tmap, entry) -> tmap.put(Long.valueOf((String) entry.getKey()), (String) entry.getValue()),
                        TreeMap::putAll);
    }

    /**
     * Translates a number into its string representation (i.e. 20 -> twenty)
     * @param number A number up to one trillion.
     * @param withAnd Whether to include the 'and' keyword in the name (i.e. four hundred and seventy two vs four hundred seventy two)
     * @return The number's name.
     */
    private String translate(final Long number, final boolean withAnd) {
        final boolean isPositive = number >= 0;
        final StringJoiner objNameBuilder = new StringJoiner(" ");

        if (!isPositive) {
            objNameBuilder.add("minus");
        }

        Long remainingNumber = Math.abs(number);
        boolean isLargerThanHundred = false;

        do {
            final Map.Entry<Long, String> objHeadEntry = getNumberHeadEntry(remainingNumber);

            if (NumberUtils.compare(objHeadEntry.getKey(), 0) != 0) {
                final Long modHeadNumber = Math.floorMod(remainingNumber, objHeadEntry.getKey());
                final Long quantifier = (remainingNumber - modHeadNumber) / objHeadEntry.getKey();
                final Long nextRemainingNumber = remainingNumber - objHeadEntry.getKey() * quantifier;

                if (remainingNumber >= 100) {
                    final boolean includeAnd = NumberUtils.compare(nextRemainingNumber, 0) == 0;
                    objNameBuilder.add(translate(quantifier, includeAnd).toLowerCase());
                } else if (isLargerThanHundred && withAnd) {
                   objNameBuilder.add(AND);
                }

                isLargerThanHundred = remainingNumber >= 100;
                remainingNumber = nextRemainingNumber;
            }

            objNameBuilder.add(objHeadEntry.getValue());

        } while (remainingNumber > 0);

        return StringUtils.capitalize(objNameBuilder.toString());
    }

    /**
     * (non-Javadoc)
     *
     * @see com.kklte.numbers.services.INumberTranslateService#translate(Long)
     */
    @Override
    public String translate(Long number) {
        return translate(number, true);
    }

    /**
     * Gets the name submap of lesser or equal number(s).
     * @param number
     * @return
     */
    private Map.Entry<Long, String> getNumberHeadEntry(final Long number) {
        return this.treeMapNumberNames.floorEntry(number);
    }
}
