package org.choongang.commons.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.choongang.member.MemberUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CommonInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        checkDevice(request);
        clearLoginData(request);

        return true;
    }

    /**
     * PC, 모바일 수동 변경 처리
     *
     * //device - PC : PC 뷰, Mobile : Mobile 뷰
     * @param request
     */
    private void checkDevice(HttpServletRequest request){

        String device = request.getParameter("device");
        if(!StringUtils.hasText(device)) {
            return;

        }

        device = device.toUpperCase().equals("Mobile") ? "MOBILE" : "PC";

        HttpSession session = request.getSession();
        session.setAttribute("device",device);
    }

    private void clearLoginData(HttpServletRequest request) {
        //request : 로그인페이지 아닐때는 세션을 지움, 세션주소를 가져옴.
        //공통 처리 인터셉터
        String URL = request.getRequestURI();
        if(URL.indexOf("/member/login") == -1) {//-1일때 지움
            HttpSession session = request.getSession();
            MemberUtil.clearLoginData(session);
        }
    }
}
