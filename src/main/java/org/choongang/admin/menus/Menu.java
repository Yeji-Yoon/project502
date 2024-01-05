package org.choongang.admin.menus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {
    private final static Map<String, List<MenuDetail>> menus;

    static {
        menus = new HashMap<>();
        //주메뉴코드
        menus.put("member", Arrays.asList(
           new MenuDetail("list", "회원목록", "/admin/member"),
           new MenuDetail("authority", "회원 권한","/admin/member/authority")
        ));
        //서브 메뉴코드
        menus.put("board", Arrays.asList(
                new MenuDetail("list","게시판 목록", "/admin/board"),
                new MenuDetail("add", "게시판 등록", "/admin/bard/add"),
                new MenuDetail("posts","게시글 관리", "/admin/board.posts")
        ));
    }

    public static List<MenuDetail> getMenus(String code) {
        return menus.get(code);
    }

}
