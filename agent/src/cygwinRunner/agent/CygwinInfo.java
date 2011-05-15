package cygwinRunner.agent;

import jetbrains.buildServer.util.FileUtil;
import jetbrains.buildServer.agent.BuildAgentConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Map;

import cygwinRunner.common.Util;


public class CygwinInfo {
    private final File myHome;

    public CygwinInfo(@NotNull final File home) {
        myHome = home;
    }

    @NotNull
    public File getHome() {
        return myHome;
    }

    @NotNull
    public String getBash() {
        return FileUtil.getCanonicalFile(new File(getHome(), "bin\\bash.exe")).getPath();
    }

    @Override
    public String toString() {
        return "Cygwin bash ( " + getBash() + ")";
    }

    public void saveInfo(@NotNull BuildAgentConfiguration config) {
        config.addConfigurationParameter(Util.CYGWIN_LOCATION, getHome().toString());
    }
   
    @Nullable
    public static CygwinInfo loadInfo(@NotNull final BuildAgentConfiguration config) {
        final Map<String, String> ps = config.getConfigurationParameters();
        final String path = ps.get(Util.CYGWIN_LOCATION);

        return new CygwinInfo(FileUtil.getCanonicalFile(new File(path)));
    }
}
