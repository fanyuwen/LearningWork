package com.learning;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author fanyuwen
 * @date 2019/6/16 18:13
 */
public class StringUtilsTest {

    @Test
    public void convertHexStrToBytesTest() {
        String byteStr = "811213";
        byte[] bytes = StringUtils.convertHexStrToBytes(byteStr);
        Assert.assertArrayEquals(bytes, new byte[]{-127, 18, 19});
    }

    //A1B2C3D4E5F60708
    @Test
    public void convertHexStrToBytesTest1() {
        String byteStr = "A1B2C3D4E5F60708";
        byte[] bytes = StringUtils.convertHexStrToBytes(byteStr);
        Assert.assertArrayEquals(bytes, new byte[]{-95, -78, -61, -44, -27, -10, 7, 8});
    }


    @Test
    public void convertBytesToHexStrTest() {
        byte[] bytes = {-127, 18, 19};
        String byteStr = StringUtils.convertBytesToHexStr(bytes);
        Assert.assertEquals(byteStr, "811213");
    }
}