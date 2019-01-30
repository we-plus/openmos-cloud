set OPENMOS_HOME=%~dp0..
echo %OPENMOS_HOME%

set OPENMOS_LOGS_DIR=%OPENMOS_HOME%\logs
mkdir %OPENMOS_LOGS_DIR%

set OPENMOS_TEMP_DIR=%OPENMOS_HOME%\temp
mkdir %OPENMOS_TEMP_DIR%

set OPENMOS_LIB_DIR=%OPENMOS_HOME%\lib
set OPENMOS_CONFIG_DIR=%OPENMOS_HOME%\conf

set OPENMOS_LOGS_SEVERITY=debug

@rem set JAVA_OPT=-DDD_OPENMOS_LOG_PRIORITY=debug -DDD_OPENMOS_LOGPATH=c:\temp -Dvertx.logger-delegate-factory-class-name=io.vertx.core.logging.Log4jLogDelegateFactory -Dhazelcast.logging.type=log4j
set JAVA_OPT=-DDD_OPENMOS_LOG_PRIORITY=%OPENMOS_LOGS_SEVERITY% -DDD_OPENMOS_LOGPATH=%OPENMOS_LOGS_DIR% -Dvertx.logger-delegate-factory-class-name=io.vertx.core.logging.Log4jLogDelegateFactory -Dhazelcast.logging.type=log4j -Dvertx.cacheDirBase=%OPENMOS_TEMP_DIR%\.vertx

@rem java %JAVA_OPT% -cp ../conf;../lib/* eu.openmos.agentcloud.starter.Starter stop
java %JAVA_OPT% -cp %OPENMOS_CONFIG_DIR%;%OPENMOS_LIB_DIR%\* eu.openmos.agentcloud.starter.Starter stop

@rem pause
