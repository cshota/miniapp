package jp.co.scsk.miniapp.sample.api.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Snow Flake Util
 *
 * @author Geng Peideng
 */

@Component
public class SnowflakeUtil {

    private static final Map<String, Long> keyMap = new HashMap<>();
    /** Base Timestamp (2021/8/1 00:00:00.000) */
    private final static long START_STAMP = 1596211200000L;

    /** Max bit count for Seq ID */
    private final static long SEQUENCE_BIT = 12;
    /** Max bit count for Machine ID */
    private final static long MACHINE_BIT = 5;
    /** Max bit count for Data Center ID */
    private final static long DC_BIT = 5;

    /** Max Seq value (4095) */
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /** Bit shift count for Machine ID */
    private final static long MACHINE_SHIFT = SEQUENCE_BIT;
    /** Bit shift count for Data Center ID */
    private final static long DATA_CENTER_SHIFT = SEQUENCE_BIT + MACHINE_BIT;
    /** Bit shift count for Timestamp */
    private final static long TIMESTAMP_SHIFT = SEQUENCE_BIT + MACHINE_BIT + DC_BIT;

    /** Last Timestamp */
    private static long lastStamp = -1L;


    /**
     * Generate Next Snowflake ID
     *
     * @return Snowflake ID
     */
    public static long getSnowflakeId() {
        long currStamp = System.currentTimeMillis();
        if (currStamp < lastStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }
        long sequenceId ;
        do{
            // Get the sequence ID By the current TimeStamp
            sequenceId = getSequenceId(currStamp);
            if(sequenceId > MAX_SEQUENCE) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currStamp = System.currentTimeMillis();
            }
        }while(sequenceId > MAX_SEQUENCE);

        // refresh last timestamp
        lastStamp = currStamp;

        // Machine ID (current fixed by 1)
        long machineId = 1L;
        // Data center ID (current fixed by 1)
        long DCId = 1L;
        // Timestamp + Machine ID + Data center ID + Seq ID
        return (currStamp - START_STAMP) << TIMESTAMP_SHIFT
                | DCId << DATA_CENTER_SHIFT
                | machineId << MACHINE_SHIFT
                | sequenceId;
    }

    /**
     * Get seq from Redis by current timestamp(ms)
     * @return The seq number
     */
    private static long getSequenceId(Long timeStamp) {
        if (keyMap.size() > 10) {
            keyMap.clear();
        }
        String key = "snowflake:" + timeStamp.toString();
        long id = !keyMap.containsKey(key) ? 0 : keyMap.get(key);
        id ++;
        keyMap.put(key, id);
        return id;
    }
}