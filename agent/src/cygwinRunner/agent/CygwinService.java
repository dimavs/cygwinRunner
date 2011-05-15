package cygwinRunner.agent;

import com.intellij.openapi.diagnostic.Logger;
import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.runner.BuildServiceAdapter;
import jetbrains.buildServer.agent.runner.ProgramCommandLine;
import org.jetbrains.annotations.NotNull;
import jetbrains.buildServer.util.FileUtil;
import jetbrains.buildServer.util.StringUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import cygwinRunner.common.Util;



public class CygwinService extends BuildServiceAdapter {
    private static final Logger LOG = Logger.getInstance(CygwinService.class.getName());

    private final CygwinInfoProvider myProvider;
    //private File myFileToRemove = null;

    public CygwinService(final CygwinInfoProvider provider) {
        myProvider = provider;
    }

    @NotNull
    @Override
    public ProgramCommandLine makeProgramCommandLine() throws RunBuildException {
        final CygwinInfo info = selectTool();
        return createProgramCommandline(selectCmd(info), getCmdArguments(info));
    }

    //@Override
    //public void afterProcessFinished() throws RunBuildException {
    //    super.afterProcessFinished();
    //
    //    if (myFileToRemove != null && !getConfigParameters().containsKey(Util.CONFIG_KEEP_GENERATED)) {
    //        FileUtil.delete(myFileToRemove);
    //        myFileToRemove = null;
    //    }
    //}


    private CygwinInfo selectTool() throws RunBuildException {
        CygwinInfo ci = myProvider.getCygwin();

        if (ci != null)
            return ci;

        throw new RunBuildException("Cygwin was not found");
    }

    @NotNull
    private String selectCmd(CygwinInfo info) {
        return info.getBash();
    }

    private List<String> getCmdArguments(@NotNull final CygwinInfo info) throws RunBuildException {
        List<String> list = new ArrayList<String>();

        final File script = getOrCreateScriptFile();
        list.add(script.getPath());

        return list;
    }

    private File getOrCreateScriptFile() throws RunBuildException {
        Closeable handle = null;
        try {
            String text = getRunnerParameters().get(Util.RUNNER_SCRIPT_CODE);
            if (StringUtil.isEmptyOrSpaces(text)) {
                throw new RunBuildException("Empty build script");
            }
            text = StringUtil.convertLineSeparators(text, "\r\n");

            final File code = FileUtil.createTempFile(getBuildTempDirectory(), "cygwin", ".bash", true);
            OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream(code), "utf-8");
            handle = w;
            w.write(text);

            return code;
        } catch (IOException e) {
            throw new RunBuildException("Failed to generate temporary file at " + getBuildTempDirectory(), e);
        } finally {
            FileUtil.close(handle);
        }
    }
}




