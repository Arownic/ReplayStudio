package com.replaymod.replaystudio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface ILogger {
    /**
     * ReplayMod is expected to replace this logger, however this is a fallback
     *
     * @return {@link org.apache.logging.log4j.Logger}
     */
    static Logger getLogger() {
        return LogManager.getLogger("ReplayStudio");
    }
}
