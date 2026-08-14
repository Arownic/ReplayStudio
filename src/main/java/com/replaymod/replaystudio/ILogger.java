package com.replaymod.replaystudio;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

public interface ILogger {

    Logger getLogger();

    // Fallback default implementation
    ILogger DEFAULT = () -> LogManager.getLogger("ReplayStudio");

    // Holds whichever implementation is currently active
    class Holder {
        private static ILogger instance = DEFAULT;
    }

    LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
    Configuration config = ctx.getConfiguration();
    LoggerConfig rootConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);

    /**
     * ReplayMod is expected to replace this logger via {@link #setLogger(ILogger)},
     * however this is a fallback that will be used until it does.
     */
    static Logger get() {
        rootConfig.setLevel(Level.INFO);
        ctx.updateLoggers();
        return Holder.instance.getLogger();
    }

    static void setLogger(ILogger logger) {
        Holder.instance = logger;
    }
}
