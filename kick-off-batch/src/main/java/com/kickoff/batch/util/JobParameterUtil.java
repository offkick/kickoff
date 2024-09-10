package com.kickoff.batch.util;

import org.springframework.batch.core.JobParameters;

import java.util.Objects;

public class JobParameterUtil {

    public static String getIfPresentStringParameter(JobParameters parameters, String target)
    {
        return target == null ? null : parameters.getString(target);
    }

    public static Long getIfPresentLongParameter(JobParameters parameters, String target)
    {
        return target == null ? null : Long.parseLong(Objects.requireNonNull(parameters.getString(target)));
    }
}
