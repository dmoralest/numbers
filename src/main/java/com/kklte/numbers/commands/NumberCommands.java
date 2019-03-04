package com.kklte.numbers.commands;

import com.kklte.numbers.services.INumberTranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * Number commands
 */
@ShellComponent(value = "numbers")
public class NumberCommands {

    @Autowired
    private INumberTranslateService numberTranslateService;

    /**
     * Translates an integer number into its string representation
     * @param number
     * @return
     */
    @ShellMethod(
            key = "translate",
            prefix = "--number",
            value = "Converts a number into its string name (i.e. 15 -> Fifteen)")
    public String translate(final Long number) {
        return this.numberTranslateService.translate(number);
    }
}
