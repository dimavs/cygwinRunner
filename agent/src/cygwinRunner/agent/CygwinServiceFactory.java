package cygwinRunner.agent;

import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;
import jetbrains.buildServer.agent.AgentBuildRunnerInfo;
import jetbrains.buildServer.agent.BuildAgentConfiguration;
import jetbrains.buildServer.agent.runner.CommandLineBuildService;
import jetbrains.buildServer.agent.runner.CommandLineBuildServiceFactory;

import cygwinRunner.common.Util; 

public class CygwinServiceFactory implements CommandLineBuildServiceFactory, AgentBuildRunnerInfo {
    private static final Logger LOG = Logger.getInstance(CygwinServiceFactory.class.getName());
    //private final CygwinInfoProvider myInfo;

    public CygwinServiceFactory(/*@NotNull final CygwinInfoProvider info*/) {
        //myInfo = info;
    }

    @NotNull
    public CommandLineBuildService createService() {
        return new CygwinService(/*myInfo*/);
    }

    @NotNull
    public AgentBuildRunnerInfo getBuildRunnerInfo() {
        return this;
    }

    @NotNull
    public String getType() {
        return Util.RUN_TYPE;
    }

    public boolean canRun(@NotNull final BuildAgentConfiguration agentConfiguration) {
        //if (myInfo.getCygwin() == null) {
        //    LOG.info("Cygwin runner is disabled: Cygwin was not found.");
        //    return false;
        //}

        return true;
    }
}
