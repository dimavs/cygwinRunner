package cygwinRunner.server;
import org.jetbrains.annotations.NotNull;

import cygwinRunner.common.Util;

public class CygwinRunnerBean {
    @NotNull
    public String getCygwinLocation() {
        return Util.CYGWIN_LOCATION;
    }

    @NotNull
    public String getCodeValue() {
        return Util.RUNNER_SCRIPT_CODE;
    }
}
