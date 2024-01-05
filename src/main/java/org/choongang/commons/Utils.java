package org.choongang.commons;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.choongang.admin.config.controllers.BasicConfig;
import org.choongang.file.service.FileInfoService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class Utils {
    private final HttpServletRequest request;
    private final HttpSession session;
    private final FileInfoService fileInfoService;

    private static final ResourceBundle commonsBundle;
    private static final ResourceBundle validationsBundle;
    private static final ResourceBundle errorsBundle;

    static {//메시지를 타입에 따라 가져옴
        commonsBundle = ResourceBundle.getBundle("messages.commons");
        validationsBundle = ResourceBundle.getBundle("messages.validations");
        errorsBundle = ResourceBundle.getBundle("messages.errors");
    }

//모바일 장비를 보고 확인
public boolean isMobile() {
    // 모바일 수동 전환 모드 체크
    String device = (String)session.getAttribute("device");
    if (StringUtils.hasText(device)) {
        return device.equals("MOBILE");
    }

    // 요청 헤더 : User-Agent
    String ua = request.getHeader("User-Agent");

    String pattern = ".*(iPhone|iPod|iPad|BlackBerry|Android|Windows CE|LG|MOT|SAMSUNG|SonyEricsson).*";

    return ua.matches(pattern);

        //return isMobile;
    }
    public String tpl(String path) {
        String prefix = isMobile() ? "mobile/" : "front/";

        return prefix + path;
    }

    public static String getMessage(String code, String type) {
        //메서드를 만들지 않아도 조회가능하게 static 사용
        type = StringUtils.hasText(type) ?type: "validations";

        ResourceBundle bundle = null;
        if(type.equals("commons")) {
            bundle = commonsBundle;
        }else if(type.equals("errors")) {
            bundle = errorsBundle;
        }else {
            bundle= validationsBundle;
        }

        return bundle.getString(code);
    }
    public static String getMessage(String code) {

        return getMessage(code,null);
    }

    /**
     * \n 또는 \r\n -> <br>
     * @param str
     * @return
     */
    public String nl2br(String str) {
        str = str.replaceAll("\\n","<br>")
                .replaceAll("\\r","");

        return str;
    }

    /**
     * 썸네일 이미지 사이즈 설정
     *
     * @return
     */
    public List<int[]> getThumbSize() {
        BasicConfig basicConfig = (BasicConfig)request.getAttribute("siteConfig");
        String thumbSize = basicConfig.getThumbSize(); // \r\n
        String[] thumbsSize = thumbSize.split("\\n");
        List<int[]> data = Arrays.stream(thumbsSize)
                .filter(StringUtils::hasText)
                .map(s -> s.replaceAll("\\s+",""))//공백제거
                .map(this::toConvert).toList();

        return data;
    }

    private int[] toConvert(String size) {
        size = size.trim();

        int[] data = Arrays.stream(size.replaceAll("\\r","").toUpperCase().split("X")).mapToInt(Integer::parseInt).toArray();

        return data;
    }

    public String printThumb(long seq, int width, int height, String className) {//주로 타임리프내에서 사용

        String[] data = fileInfoService.getThumb(seq,width,height);
        if(data != null) {
            String cls = StringUtils.hasText(className) ? "class='" + className + "'":"";
            String image = String.format("<img src='%s'%s>",data[1], cls);

            return image;
        }

        return "";

    }

    public String printThumb(long seq, int width, int height) {
        return printThumb(seq,width,height,null);
    }
}
