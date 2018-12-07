package com.cloud.common.jwt;

import com.cloud.common.basic.ResponseCode;
import com.cloud.common.model.User;
import io.jsonwebtoken.*;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static RSAPrivateKey priKey;
    private static RSAPublicKey pubKey;

    private static class SingletonHolder{
        private static final JwtUtil Instance = new JwtUtil();
    }

    public synchronized static JwtUtil getInstance(String modulus,String primaryExponent,String publicExponent){
        if(priKey == null && priKey == null){
            priKey = RSAUtils.getPrivateKey(modulus,primaryExponent);
            pubKey = RSAUtils.getPublicKey(modulus,publicExponent);
        }
        return SingletonHolder.Instance;
    }

    public synchronized static void reload(String modulus,String privateExponent,String publicExponent){
        priKey = RSAUtils.getPrivateKey(modulus,privateExponent);
        pubKey = RSAUtils.getPublicKey(modulus,publicExponent);
    }
    public synchronized static JwtUtil getInstance(){
        if(priKey == null && priKey == null){
            priKey = RSAUtils.getPrivateKey(RSAUtils.modulus,RSAUtils.private_exponent);
            pubKey = RSAUtils.getPublicKey(RSAUtils.modulus,RSAUtils.public_exponent);
        }
        return SingletonHolder.Instance;
    }

    /*
    获取token
    @Param uid用户id
    @Param exp 失效时间 分钟
     */
    public static String getToken(User user ,int exp){
        long endTime = System.currentTimeMillis() + 1000 * 60 * exp;
        Map<String,Object> map = new HashMap<>();
        map.put("username",user.getName());
        map.put("password",user.getPassword());
        return Jwts.builder().setClaims(map).setExpiration(new Date(endTime)).signWith(SignatureAlgorithm.RS512,priKey).compact();
    }

    public static String getToken(User user){
        long endTime = System.currentTimeMillis() + 1000 * 60 * 24 * 60;  //默认过期时间24小时
        Map<String,Object> map = new HashMap<>();
        map.put("username",user.getName());
        map.put("password",user.getPassword());
        return Jwts.builder().setClaims(map).setExpiration(new Date(endTime)).signWith(SignatureAlgorithm.RS512,priKey).compact();
    }
    /*
    检查token是否非法
     */
    public JWTResult checkToken(String token){
        try{
            Claims claims = Jwts.parser().setSigningKey(pubKey).parseClaimsJwt(token).getBody();
            String sub = claims.get("sub",String.class);
            return new JWTResult(true,sub,"合法请求", ResponseCode.SUCCESS_CODE.getCode());
        }catch (ExpiredJwtException e){
            return new JWTResult(false,null,"token过期",ResponseCode.TOKEN_TIMEOUT_ERROR.getCode());
        }catch (SignatureException e){
            return new JWTResult(false,null,"非法请求",ResponseCode.NO_AUTH_ERROR.getCode());
        }catch (Exception e){
            return new JWTResult(false,null,"非法请求",ResponseCode.NO_AUTH_ERROR.getCode());
        }
    }

    public Claims getClaimsFromToken(String token){
        Claims claims;
        try{
            claims = Jwts.parser().setSigningKey(pubKey).parseClaimsJwt(token).getBody();
        }catch (Exception e){
            claims = null;
        }
        return claims;
    }
    public static class JWTResult{
        private boolean status;
        private String uid;
        private String msg;
        private int code;
        public JWTResult() {
            super();
        }

        public JWTResult(boolean status, String uid, String msg, int code) {
            super();
            this.status = status;
            this.uid = uid;
            this.msg = msg;
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
