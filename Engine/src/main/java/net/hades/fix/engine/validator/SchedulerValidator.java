/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SchedulerValidator.java
 *
 * $Id: SchedulerValidator.java,v 1.1 2011-03-28 04:38:37 vrotaru Exp $
 */
package net.hades.fix.engine.validator;

import net.hades.fix.engine.config.model.ScheduleDateInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.hades.fix.engine.config.model.CounterpartyInfo;
import net.hades.fix.engine.config.model.HadesInstanceInfo;
import net.hades.fix.engine.config.model.ScheduleCalendarInfo;
import net.hades.fix.engine.config.model.ScheduleTaskInfo;
import net.hades.fix.engine.config.model.SessionInfo;

/**
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public class SchedulerValidator extends Validator {

    private static final char[] ALLOWED_DATE_CHARS = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-'};

    public SchedulerValidator(Map<String, String> errors) {
        super(errors);
    }

    public SchedulerValidator() {
        super();
    }

    @Override
    public Map<String, String> validate(Object data) {
        HadesInstanceInfo configuration = (HadesInstanceInfo) data;
        validateUniqueSchedulerTaskNames(configuration);
        validateCalendarFields(configuration);
        validateTaskFields(configuration);
        
        return errors;
    }

    private void validateUniqueSchedulerTaskNames(HadesInstanceInfo configuration) {
        List<String> taskNames = new ArrayList<String>();
        int noOccur = 0;
        if (configuration.getScheduler() != null) {
            if (configuration.getScheduler().getTasks() != null && configuration.getScheduler().getTasks().length > 0) {
                for (ScheduleTaskInfo task : configuration.getScheduler().getTasks()) {
                    if (!taskNames.contains(task.getName())) {
                        taskNames.add(task.getName());
                    } else {
                        ++noOccur;
                        errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.DuplicateTask, noOccur),
                                "Duplicate schedule task names are not allowed. Duplicate task name : " + task.getName());
                    }
                }
            }
        }
        for (CounterpartyInfo cpty : configuration.getCounterparties()) {
            for (SessionInfo sess : cpty.getSessions()) {
                if (sess.getSchedules() != null && sess.getSchedules().length > 0) {
                    for (ScheduleTaskInfo task : sess.getSchedules()) {
                        if (!taskNames.contains(task.getName())) {
                            taskNames.add(task.getName());
                        } else {
                            ++noOccur;
                            errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.DuplicateTask, noOccur),
                                    "Duplicate schedule task names are not allowed. Duplicate task name : " + task.getName());
                        }
                    }
                }
            }
        }
    }

    private void validateCalendarFields(HadesInstanceInfo configuration) {
        int noOccur = 0;
        if (configuration.getScheduler() != null) {
            if (configuration.getScheduler().getCalendars() != null && configuration.getScheduler().getCalendars().length > 0) {
                for (ScheduleCalendarInfo calendar : configuration.getScheduler().getCalendars()) {
                    if (calendar.getExcludes() != null && calendar.getExcludes().length > 0) {
                        for (ScheduleDateInfo date : calendar.getExcludes()) {
                            if (date.getDayOfMonth() == null) {
                                ++noOccur;
                                errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.MissingDayOfMonthField, noOccur),
                                        "Missing [dayOfMonth] field for calendar [" + calendar.getName() + "] exclusion.");
                            } else {
                                if (!checkAllowedChars(date.getDayOfMonth())) {
                                    ++noOccur;
                                    errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.NonAllowedChar, noOccur),
                                            "Not allowed character in [dayOfMonth] field [" + date.getDayOfMonth() + "] for calendar ["
                                            + calendar.getName() + "] exclusion.");
                                }
                                if (!checkDayOfMonth(date.getDayOfMonth())) {
                                    ++noOccur;
                                    errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.BadDateFormat, noOccur),
                                            "A value or interval [0-31] must be set in [dayOfMonth] field [" + date.getDayOfMonth() + "] for calendar ["
                                            + calendar.getName() + "] exclusion.");
                                }
                            }
                            if (date.getMonth() == null) {
                                ++noOccur;
                                errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.MissingMonthField, noOccur),
                                        "Missing [month] field for calendar [" + calendar.getName() + "] exclusion.");
                            } else {
                                if (!checkAllowedChars(date.getMonth())) {
                                    ++noOccur;
                                    errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.NonAllowedChar, noOccur),
                                            "Not allowed character in [month] field [" + date.getMonth() + "] for calendar ["
                                            + calendar.getName() + "] exclusion.");
                                }
                                if (!checkMonth(date.getMonth())) {
                                    ++noOccur;
                                    errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.BadDateFormat, noOccur),
                                            "A value or interval [1-12] must be set in [month] field [" + date.getMonth() + "] for calendar ["
                                            + calendar.getName() + "] exclusion.");
                                }
                            }
                            if (date.getYear() == null) {
                                ++noOccur;
                                errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.MissingYearField, noOccur),
                                        "Missing [year] field for calendar [" + calendar.getName() + "] exclusion.");
                            } else {
                                if (!checkAllowedChars(date.getYear())) {
                                    ++noOccur;
                                    errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.NonAllowedChar, noOccur),
                                            "Not allowed character in [year] field [" + date.getYear() + "] for calendar ["
                                            + calendar.getName() + "] exclusion.");
                                }
                                if (!checkYear(date.getYear())) {
                                    ++noOccur;
                                    errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.BadDateFormat, noOccur),
                                            "A valid year or interval must be set in [year] field [" + date.getYear() + "] for calendar ["
                                            + calendar.getName() + "] exclusion.");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void validateTaskFields(HadesInstanceInfo configuration) {
        int noOccur = 0;
        if (configuration.getScheduler() != null) {
            if (configuration.getScheduler().getTasks() != null && configuration.getScheduler().getTasks().length > 0) {
                for (ScheduleTaskInfo task : configuration.getScheduler().getTasks()) {
                    ScheduleDateInfo date = task.getDate();
                    if (date.getDayOfMonth() != null) {
                        if (!checkAllowedChars(date.getDayOfMonth())) {
                            ++noOccur;
                            errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.NonAllowedChar, noOccur),
                                    "Not allowed character in [dayOfMonth] field [" + date.getDayOfMonth() + "] for task ["
                                    + task.getName() + "].");
                        }
                        if (!checkDayOfMonth(date.getDayOfMonth())) {
                            ++noOccur;
                            errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.BadDateFormat, noOccur),
                                    "A value or interval [0-31] must be set in [dayOfMonth] field [" + date.getDayOfMonth() + "] for task ["
                                    + task.getName() + "].");
                        }
                    }
                    if (date.getMonth() != null) {
                        if (!checkAllowedChars(date.getMonth())) {
                            ++noOccur;
                            errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.NonAllowedChar, noOccur),
                                    "Not allowed character in [month] field [" + date.getMonth() + "] for task ["
                                    + task.getName() + "].");
                        }
                        if (!checkMonth(date.getMonth())) {
                            ++noOccur;
                            errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.BadDateFormat, noOccur),
                                    "A value or interval [1-12] must be set in [month] field [" + date.getMonth() + "] for task ["
                                    + task.getName() + "].");
                        }
                    }
                    if (date.getYear() != null) {
                        if (!checkAllowedChars(date.getYear())) {
                            ++noOccur;
                            errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.NonAllowedChar, noOccur),
                                    "Not allowed character in [year] field [" + date.getYear() + "] for task ["
                                    + task.getName() + "].");
                        }
                        if (!checkYear(date.getYear())) {
                            ++noOccur;
                            errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.BadDateFormat, noOccur),
                                    "A valid year or interval must be set in [year] field [" + date.getYear() + "] for task ["
                                    + task.getName() + "].");
                        }
                    }
                    if (date.getDayOfWeek() != null) {
                        if (!checkAllowedChars(date.getDayOfWeek())) {
                            ++noOccur;
                            errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.NonAllowedChar, noOccur),
                                    "Not allowed character in [dayOfWeek] field [" + date.getYear() + "] for task ["
                                    + task.getName() + "].");
                        }
                    }
                    if (date.getHour() != null) {
                        try {
                            int hour = Integer.valueOf(date.getHour());
                            if (hour < 0 || hour > 23) {
                                ++noOccur;
                                errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.BadDateFormat, noOccur),
                                        "A value in interval [0-24] must be set in [hour] field [" + date.getHour() + "] for task ["
                                        + task.getName() + "].");
                            }
                        } catch (NumberFormatException ex) {
                            ++noOccur;
                            errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.NonAllowedChar, noOccur),
                                    "Not allowed character in [hour] field [" + date.getHour() + "] for task ["
                                    + task.getName() + "].");
                        }
                    } else {
                        ++noOccur;
                        errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.MissingHourField, noOccur),
                                "Missing [hour] field for task [" + task.getName() + "].");
                    }
                    if (date.getMinute() != null) {
                        try {
                            int minute = Integer.valueOf(date.getMinute());
                            if (minute < 0 || minute > 59) {
                                ++noOccur;
                                errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.BadDateFormat, noOccur),
                                        "A value in interval [0-59] must be set in [minute] field [" + date.getHour() + "] for task ["
                                        + task.getName() + "].");
                            }
                        } catch (NumberFormatException ex) {
                            ++noOccur;
                            errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.NonAllowedChar, noOccur),
                                    "Not allowed character in [minute] field [" + date.getMinute() + "] for task ["
                                    + task.getName() + "].");
                        }
                    } else {
                        ++noOccur;
                        errors.put(getErrorKey(ValidatorType.Scheduler, ErrorCode.MissingMinuteField, noOccur),
                                "Missing [minute] field for task [" + task.getName() + "].");
                    }
                }
            }
        }
    }

    private boolean checkAllowedChars(String string) {
        boolean result = true;
        for (char c : string.toCharArray()) {
            boolean found = false;
            for (char c1 : ALLOWED_DATE_CHARS) {
                if (c == c1) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                result = false;
                break;
            }
        }

        return result;
    }

    private boolean checkDayOfMonth(String dayOfMonth) {
        if (dayOfMonth.indexOf("-") > 0) {
            String[] days = dayOfMonth.split("-");
            if (days.length != 2) {
                return false;
            }
            if (days[0].length() == 0 || days[1].length() == 0) {
                return false;
            }
            int day = Integer.valueOf(days[0]);
            if (day < 0 || day > 31) {
                return false;
            }
            day = Integer.valueOf(days[1]);
            if (day < 0 || day > 31) {
                return false;
            }
        } else {
            int day = Integer.valueOf(dayOfMonth);
            if (day < 0 || day > 31) {
                return false;
            }
        }

        return true;
    }

    private boolean checkMonth(String month) {
        if (month.indexOf("-") > 0) {
            String[] months = month.split("-");
            if (months.length != 2) {
                return false;
            }
            if (months[0].length() == 0 || months[1].length() == 0) {
                return false;
            }
            int mon = Integer.valueOf(months[0]);
            if (mon < 1 || mon > 12) {
                return false;
            }
            mon = Integer.valueOf(months[1]);
            if (mon < 1 || mon > 12) {
                return false;
            }
        } else {
            int mon = Integer.valueOf(month);
            if (mon < 1 || mon > 12) {
                return false;
            }
        }

        return true;
    }

    private boolean checkYear(String year) {
        if (year.indexOf("-") > 0) {
            String[] years = year.split("-");
            if (years.length != 2) {
                return false;
            }
            if (years[0].length() == 0 || years[1].length() == 0) {
                return false;
            }
            if (years[0].length() != 4 || years[1].length() != 4) {
                return false;
            }
            int yr1 = Integer.valueOf(years[0]);
            if (yr1 < 1900 || yr1 > 3000) {
                return false;
            }
            int yr2 = Integer.valueOf(years[1]);
            if (yr2 < 1900 || yr2 > 3000) {
                return false;
            }
            if (yr1 > yr2) {
                return false;
            }
        } else {
            if (year.length() != 4) {
                return false;
            }
            int yr = Integer.valueOf(year);
            if (yr < 1900 || yr > 3000) {
                return false;
            }
        }

        return true;
    }
}
