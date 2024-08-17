package com.kickoff.batch.util;

import org.springframework.batch.core.JobParameters;

public class JobParameterUtil {

    public static String getIfPresentStringParameter(JobParameters parameters, String target)
    {
        return target == null ? null : parameters.getString(target);
    }

    public static Long getIfPresentLongParameter(JobParameters parameters, String target)
    {
        return target == null ? null : parameters.getLong(target);
    }
}
