package com.fivet.buddy.util;

// 출력 목록의 페이지를 만들어주는 util
public class PageNavi {
    public String getPageNaviAll(int currentPage, int recordCountPerPage,
        int naviCountPerPage, int recordTotalCount, String url, String param) throws Exception {

        int pageTotalCount = 0;
        if (recordTotalCount % recordCountPerPage > 0) {
            pageTotalCount = (recordTotalCount / recordCountPerPage) + 1;
        } else {
            pageTotalCount = recordTotalCount / recordCountPerPage;
        }

        if (currentPage < 1) {
            currentPage = 1;
        } else if (currentPage > pageTotalCount) {
            currentPage = pageTotalCount;
        }

        int startNavi = (currentPage - 1) / naviCountPerPage * naviCountPerPage + 1;

        int endNavi = startNavi + naviCountPerPage - 1;


        if(endNavi > pageTotalCount) {
            endNavi = pageTotalCount;
        }

        boolean needPrev = true;
        boolean needNext = true;

        if(startNavi == 1) {
            needPrev = false;
        }
        if(endNavi == pageTotalCount) {
            needNext = false;
        }

        StringBuilder sb = new StringBuilder();

        if (needPrev) {
            sb.append("<a href='").append(url).append("?").append(param).append("=").append(startNavi-1).append("'> < </a> ");
        }

        for (int i=startNavi; i<=endNavi; i++) {
            sb.append("<a href='").append(url).append("?").append(param).append("=").append(i).append("'>").append(i).append("</a> ");
        }

        if (needNext) {
            sb.append("<a href='").append(url).append("?").append(param).append("=").append(endNavi+1).append("'> > </a>");
        }
        return sb.toString();
        //SQL문
        //select * from (select TABLE.*, row_number() over(order by 1 desc) rn from TABLE) where rn between #{start} and #{end}
        // start(페이지의 시작글번호) = (cpage-1) * rcpp + 1, end(페이지의 끝번호) = cpage * rcpp
    }
}
