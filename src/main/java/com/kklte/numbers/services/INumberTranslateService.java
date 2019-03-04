package com.kklte.numbers.services;

public interface INumberTranslateService {

    /**
     * Translates a number into its string representation (i.e. 20 -> twenty)
     * @param number A number up to one trillion.
     * @return The number's name.
     */
    String translate(Long number);
}
