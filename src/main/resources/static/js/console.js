// 관리자 창에서 콘솔로그로 값 출력 막기
console = {};
console.log = function(){};
console.warn = function(){};
console.error = function(){};