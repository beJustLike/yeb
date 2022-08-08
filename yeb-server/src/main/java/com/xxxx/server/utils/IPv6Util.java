package com.xxxx.server.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.googlecode.ipv6.IPv6Network;
import com.sun.deploy.util.StringUtils;

/**
 * @Author liyongkang
 * @Date 2022/8/8
 *
 * IPv6的工具类:
 * 1. 判断是否是IPv6类型
 * 2. 将给定的IPv6字符串转化为二进制
 * 3. 将给定的IPv6字符串转换为十六进制
 * 4. 补全带"::"的IPv6
 *
 **/
public class IPv6Util {

    /**
     * 给定16进制数据，转换为10进制数据
     * @param ipv6
     * @return
     */
    public static BigInteger ipv6ToBytesToBigInteger(String ipv6) {
        byte[] ret = new byte[17];
        ret[0] = 0;
        int ib = 16;
        boolean comFlag = false;// ipv4混合模式标记
        if (ipv6.startsWith(":"))// 去掉开头的冒号
            ipv6 = ipv6.substring(1);
        if (ipv6.endsWith("::")) {
            ipv6 = ipv6+"0";
        }
        String groups[] = ipv6.split(":");
        for (int ig = groups.length - 1; ig > -1; ig--) {// 反向扫描
            if (groups[ig].contains(".")) {
                // 出现ipv4混合模式
                byte[] temp = ipv4ToBytes(groups[ig]);
                ret[ib--] = temp[4];
                ret[ib--] = temp[3];
                ret[ib--] = temp[2];
                ret[ib--] = temp[1];
                comFlag = true;
            } else if ("".equals(groups[ig])) {
                // 出现零长度压缩,计算缺少的组数
                int zlg = 9 - (groups.length + (comFlag ? 1 : 0));
                while (zlg-- > 0) {// 将这些组置0
                    ret[ib--] = 0;
                    ret[ib--] = 0;
                }
            } else {
                int temp = Integer.parseInt(groups[ig], 16);
                ret[ib--] = (byte) temp;
                ret[ib--] = (byte) (temp >> 8);
            }
        }
        return new BigInteger(ret);
    }


    /**
     * 将ipv6每段补齐4位，如果返回-1说明给定的ipv6不合法
     *
     */
    public static String completIpv6(String ipv6) throws UnknownHostException {
        StringBuffer str = new StringBuffer();
        if (isValidIpv6Addr(ipv6)) {
            String ip = InetAddress.getByName(ipv6).toString().replace("/", "");
            String[] info = ip.split(":");
            for (int i = 0; i < info.length; i++) {
                switch (info[i].length()) {
                    case 1:
                        info[i] = "000" + info[i];
                        break;
                    case 2:
                        info[i] = "00" + info[i];
                        break;
                    case 3:
                        info[i] = "0" + info[i];
                        break;
                    default:
                        break;
                }
                if (i < 7) {
                    str.append(info[i] + ":");
                } else {
                    str.append(info[i]);
                }
            }
        }else {
            return "-1";
        }
        return str.toString();
    }

    /**
     * 将ipv6地址压缩为最简模式 ，如果返回-1说明给定的ipv6不合法
     * IPv6简写规范：
     * 1） 每个IPv6地址段起始的0可以被省略；
     * 2） 如果一段为4个零，可以简写为一个0
     * 3）如果有连续的多个段全为0，则可以使用::表示 注：一个地址段中只能有一个::出现，不可以出现两个及以上
     */

    public static String shortedIpv6(String ipv6) {
        if (isValidIpv6Addr(ipv6)) {
            String shortIP="";
            String[] arr = ipv6.split(":");
            //去掉每组数据前的0
            for (int i = 0; i < arr.length; i++){
                arr[i] = arr[i].replaceAll("^0{1,3}", "");
            }
            //最长的连续0
            String[] arr2 = arr.clone();
            for (int i = 0; i < arr2.length; i++){
                if (!"0".equals(arr2[i])){
                    arr2[i] = "-";
                }
            }
            Pattern pattern = Pattern.compile("0{2,}");
            Matcher matcher = pattern.matcher(StringUtils.join(Arrays.asList(arr2), ""));
            String maxStr= "";
            int start = -1;
            int end = -1;
            while (matcher.find()) {
                if(maxStr.length()<matcher.group().length()) {
                    maxStr=matcher.group();
                    start = matcher.start();
                    end = matcher.end();
                }
            }
            // 组合IPv6简写地址
            if(maxStr.length()>0) {
                for (int i = start; i < end; i++){
                    arr[i] = ":";
                }
            }
            shortIP = StringUtils.join(Arrays.asList(arr), ":");
            shortIP= shortIP.replaceAll(":{2,}", "::");
            return shortIP;
        }
        return "-1";
    }

    /**
     * 将整数形式的ipv6地址转换为字符串形式
     * @param big 10进制的ipv6地址
     * @return
     */
    public static String int10Toipv6(BigInteger big) {

        String str = "";
        BigInteger ff = BigInteger.valueOf(0xffff);
        for (int i = 0; i < 8; i++) {
            str = big.and(ff).toString(16) + ":" + str;

            big = big.shiftRight(16);
        }
        str = str.substring(0, str.length() - 1);

        return str.replaceFirst("(^|:)(0+(:|$)){2,8}", "::");
    }

    /**
     * 将ipv6地址转换为二进制分段（为了创造ip段写的），如果返回-1说明给定的ipv6不合法
     * @param ipv6
     * @return
     * @throws UnknownHostException
     */
    public static String subsectionIpv6To2(String ipv6) throws UnknownHostException {
        if (isValidIpv6Addr(ipv6)) {
            ipv6 = completIpv6(ipv6);
            String[] split = ipv6.split(":");
            String tmpString = "";
            if (split.length == 8) {
                for (int i = 0; i < split.length; i++) {
                    String str = Long.toBinaryString(Long.valueOf(split[i], 16));
                    tmpString += str + ":";
                }
            }
            return tmpString.substring(0,tmpString.lastIndexOf(":"));
        }
        return "-1";
    }

    /**
     * 将ipv4地址转换为二进制分段（为了创造ip段写的)
     * @param ipv4
     * @return
     * @throws UnknownHostException
     */
    public static String subsectionIvp4To2(String ipv4) throws UnknownHostException {
        String[] split = ipv4.split("\\.");
        String tmpString = "";
        if (split.length == 4) {
            for (int i = 0; i < split.length; i++) {
                String str = Long.toBinaryString(Long.valueOf(split[i]));
                tmpString += str + ":";
            }
        }
        return tmpString;
    }

    /**
     * 校验某ipv4，是否在网段掩码中
     * @param ipv4 例：172.16.0.4
     * @param cidr 例: 172.16.0.0/32
     * @return
     */
    public static boolean ipv4IsInRange(String ipv4, String cidr) {
        String[] ips = ipv4.split("\\.");
        int ipAddr = (Integer.parseInt(ips[0]) << 24) | (Integer.parseInt(ips[1]) << 16)
                | (Integer.parseInt(ips[2]) << 8) | Integer.parseInt(ips[3]);
        int type = Integer.parseInt(cidr.replaceAll(".*/", ""));
        int mask = 0xFFFFFFFF << (32 - type);
        String cidrIp = cidr.replaceAll("/.*", "");
        String[] cidrIps = cidrIp.split("\\.");
        int cidrIpAddr = (Integer.parseInt(cidrIps[0]) << 24) | (Integer.parseInt(cidrIps[1]) << 16)
                | (Integer.parseInt(cidrIps[2]) << 8) | Integer.parseInt(cidrIps[3]);

        return (ipAddr & mask) == (cidrIpAddr & mask);
    }

    /**
     * 校验某ipv6，是否在网段掩码中
     * @param ipv6 例：2444:4411:1000::0eff:1
     * @param ipv6area 例: 2444:4411:1000::1/100
     * @return
     * @throws Exception
     */
    public static boolean ipv6IsInRange(String ipv6, String ipv6area) throws Exception {
        //ipv6 = completIpv6(ipv6);
        int suffix = 0;
        if (ipv6area.contains("/")) {
            suffix = Integer.parseInt(ipv6area.substring(ipv6area.indexOf("/") + 1));
            ipv6area = ipv6area.substring(0, ipv6area.indexOf("/"));
        }
        BigInteger ipv6Big = ipv6ToBytesToBigInteger(ipv6);
        BigInteger ipv6areaBig = ipv6ToBytesToBigInteger(ipv6area);

        BigDecimal ss = new BigDecimal(2);
        BigDecimal pow = ss.pow(128);
        BigInteger aa = new BigInteger(pow.toString());

        String str = aa.toString(2).replaceAll("0", "1").substring(1);
        BigInteger bb = new BigInteger(str, 2);
        String str1 = bb.shiftLeft(128 - suffix).toString(2);
        String str2 = str1.substring(128 - suffix, str1.length());

        BigInteger mask = new BigInteger(str2, 2);

        return ipv6Big.and(mask).compareTo(ipv6areaBig.and(mask)) == 0 ? true : false;
    }

    /**
     * 判断IPv6是否属于某个IPv6段
     *
     * @throws Exception
     */
    public static boolean ipv6IsInRangeLit(String ipv6, List<String> ipv6area) throws Exception {
        for (String ipArea : ipv6area) {
            if (ipv6IsInRange(ipv6, ipArea)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBelongIpareaData(BigInteger ipv6, List<BigInteger[]> data) {
        for (BigInteger[] tmp : data) {
            if ((ipv6.compareTo(tmp[0]) == 1 || ipv6.compareTo(tmp[0]) == 0)
                    && (ipv6.compareTo(tmp[1]) == -1 || ipv6.compareTo(tmp[1]) == 0)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 给定ipv6及前缀标识，获取起止ipv6十进制数
     * @param iparea 例：2444:4411:1000::1/100
     * @return
     */
    public static BigInteger[] getIpv6Long(String iparea) {
        try {
            iparea = iparea.trim();
            BigInteger[] info = new BigInteger[2];
            // 判断是否是具体IP
            int index = iparea.indexOf("/");
            if (index < 0) {
                info[0] = ipv6ToBytesToBigInteger(iparea);
                info[1] = ipv6ToBytesToBigInteger(iparea);
                if (info[0] == null || info[1] == null) {
                    return null;
                }
                return info;
            }
            String ipinfo = iparea.substring(0, index);
            Integer count = Integer.valueOf(iparea.substring(index + 1));
            BigInteger small = BigInteger.ZERO;
            BigInteger big = BigInteger.ZERO;
            StringBuffer mask = new StringBuffer();
            for (int i = 0; i < 128; i++) {
                if (i<count) {
                    mask.append("1");
                }else {
                    mask.append("0");
                }
            }
            String string = new BigInteger(mask.toString(),2).toString(10);
			for (int i = 0; i < 128 - count; i++) {
				double pow = Math.pow(2d, Double.valueOf(Integer.valueOf(i).toString()));
				BigInteger tmp = BigDecimal.valueOf(pow).toBigInteger();
				small = small.add(tmp);
			}
			big = BigDecimal.valueOf(Math.pow(2d, Double.valueOf("128"))).toBigInteger().subtract(small)
					.subtract(BigInteger.valueOf(1));

            BigInteger areaIp = ipv6ToBytesToBigInteger(ipinfo);

            info[0] = areaIp.and(big);
            info[1] = areaIp.or(small);

            info[0] = areaIp.and(new BigInteger(string));
            String re = mask.substring(mask.lastIndexOf("1")+1).replace("0", "1");
            info[1] = areaIp.or(new BigInteger(new BigInteger(re.toString(),2).toString(10)).subtract(BigInteger.valueOf(1)));
            if (info[0] == null || info[1] == null) {
                return null;
            }
            return info;
        } catch (Exception e) {
            return null;
        }
    }



    /**
     * 根据开始ip，数量返回需要规划的所有ipv6
     * @param ipv6Start ipv6开始ip
     * @param count 添加的数量
     * @return
     * @throws UnknownHostException
     */
    public static List<String> getIpv6List(String ipv6Start , BigInteger count) throws UnknownHostException {
        List<String> list = new ArrayList<>();
        if(isValidIpv6Addr(ipv6Start)) {
            String ipv6 = completIpv6(ipv6Start);
            BigInteger ipv6IntegerStart = ipv6ToBytesToBigInteger(ipv6);
            BigInteger cou = count.subtract(new BigInteger(String.valueOf(1)));
            BigInteger ipv6IntegerEnd = ipv6IntegerStart.add(cou);

            while(true){
                String ip = int10Toipv6(ipv6IntegerStart);
                list.add(ip);
                //+1操作
                ipv6IntegerStart = ipv6IntegerStart.add(BigInteger.ONE);
                if(ipv6IntegerStart.compareTo(ipv6IntegerEnd)==1) {
                    break;
                }
            }
        }
        return list;
    }

    /**
     * 判断ipv6是否合法
     *
     * @param ipAddr
     * @return
     */
    public static boolean isValidIpv6Addr(String ipAddr) {

        String regex = "^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:)|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}(:[0-9A-Fa-f]{1,4}){1,2})|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){1,3})|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){1,4})|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){1,5})|([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){1,6})|(:(:[0-9A-Fa-f]{1,4}){1,7})|(([0-9A-Fa-f]{1,4}:){6}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){0,1}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){0,2}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){0,3}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){0,4}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(:(:[0-9A-Fa-f]{1,4}){0,5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}))$";

        if (ipAddr == null || ipAddr.trim().length() == 0) {
            System.out.println("ipv6 addresss is null ");
            return false;
        }
        ipAddr = Normalizer.normalize(ipAddr, Form.NFKC);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ipAddr);

        boolean match = matcher.matches();
        if (!match) {
            // System.out.println("invalid ipv6 addresss = " + ipAddr);
        }

        return match;
    }

    /**
     * 给定前缀标识，返回ipv6开始ip
     * @param ipAddr 例如：15ba:db5:5::/64
     * @return
     */
    public static String ipv6NetWorkStart(String ipAddr) {
        IPv6Network network = IPv6Network.fromString(ipAddr);
        String start = network.getFirst().toLongString();
        return start;
    }
    /**
     * 给定前缀标识，返回ipv6结束ip
     * @param ipAddr 例如：15ba:db5:5::/64
     * @return
     */
    public static String ipv6NetWorkEnd(String ipAddr) {
        IPv6Network network = IPv6Network.fromString(ipAddr);
        String end = network.getLast().toLongString();
        return end;
    }



    /**
     * ipv4 ：给定两个初始ip和结束ip，，计算求得子网掩码 IP段
     *
     * @param startIp
     * @param endIp
     * @return
     */
    public static List<String> range2cidrlist(String startIp, String endIp) {
        long start = ipToLong(startIp);
        long end = ipToLong(endIp);

        ArrayList<String> pairs = new ArrayList<String>();
        while (end >= start) {
            byte maxsize = 32;
            while (maxsize > 0) {
                long mask = CIDR2MASK[maxsize - 1];
                long maskedBase = start & mask;

                if (maskedBase != start) {
                    break;
                }

                maxsize--;
            }
            double x = Math.log(end - start + 1) / Math.log(2);
            byte maxdiff = (byte) (32 - Math.floor(x));
            if (maxsize < maxdiff) {
                maxsize = maxdiff;
            }
            String ip = longToIp(start);
            pairs.add(ip + "/" + maxsize);
            start += Math.pow(2, (32 - maxsize));
        }
        return pairs;
    }

    public static final int[] CIDR2MASK = new int[] { 0x00000000, 0x80000000, 0xC0000000, 0xE0000000, 0xF0000000,
            0xF8000000, 0xFC000000, 0xFE000000, 0xFF000000, 0xFF800000, 0xFFC00000, 0xFFE00000, 0xFFF00000, 0xFFF80000,
            0xFFFC0000, 0xFFFE0000, 0xFFFF0000, 0xFFFF8000, 0xFFFFC000, 0xFFFFE000, 0xFFFFF000, 0xFFFFF800, 0xFFFFFC00,
            0xFFFFFE00, 0xFFFFFF00, 0xFFFFFF80, 0xFFFFFFC0, 0xFFFFFFE0, 0xFFFFFFF0, 0xFFFFFFF8, 0xFFFFFFFC, 0xFFFFFFFE,
            0xFFFFFFFF };

    private static long ipToLong(String strIP) {
        long[] ip = new long[4];
        String[] ipSec = strIP.split("\\.");
        for (int k = 0; k < 4; k++) {
            ip[k] = Long.valueOf(ipSec[k]);
        }

        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    private static String longToIp(long ipaddress) {
        StringBuffer sb = new StringBuffer("");
        sb.append(String.valueOf((ipaddress >>> 24)));
        sb.append(".");
        sb.append(String.valueOf((ipaddress & 0x00FFFFFF) >>> 16));
        sb.append(".");
        sb.append(String.valueOf((ipaddress & 0x0000FFFF) >>> 8));
        sb.append(".");
        sb.append(String.valueOf((ipaddress & 0x000000FF)));
        return sb.toString();
    }

    private static byte[] ipv4ToBytes(String ipv4) {
        byte[] ret = new byte[5];
        ret[0] = 0;
        // 先找到IP地址字符串中.的位置
        int position1 = ipv4.indexOf(".");
        int position2 = ipv4.indexOf(".", position1 + 1);
        int position3 = ipv4.indexOf(".", position2 + 1);
        // 将每个.之间的字符串转换成整型
        ret[1] = (byte) Integer.parseInt(ipv4.substring(0, position1));
        ret[2] = (byte) Integer.parseInt(ipv4.substring(position1 + 1,
                position2));
        ret[3] = (byte) Integer.parseInt(ipv4.substring(position2 + 1,
                position3));
        ret[4] = (byte) Integer.parseInt(ipv4.substring(position3 + 1));
        return ret;
    }

}
