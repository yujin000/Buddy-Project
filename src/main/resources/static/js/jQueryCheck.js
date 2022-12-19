// jQuery 존재하는지 판단하여 없으면 스크립트 CDN 삽입
if (typeof jQuery == "undefined") {
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = "https://code.jquery.com/jquery-3.6.1.min.js";
    document.getElementsByTagName("head")[0].appendChild(script);
} else {
    // jQuery 가 이미 존재하면 출력
    console.log("jQuery Ready");
}