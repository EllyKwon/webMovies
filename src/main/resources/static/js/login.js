let crawlingData = [];
const id = document.querySelector('#user-id');
const loginForm = document.querySelector('#loginForm');
const pwd = document.querySelector('#pwd');
const loginButton = document.querySelector('.loginButton');
const poster = document.querySelector('#poster');
const checkSaveId = document.querySelector('#checkSaveId');
const moveRegister = document.querySelector('.moveRegister');
const moveMain = document.querySelector('.moveMain');
// $( document ).ready()와 유사한 코드
document.addEventListener('DOMContentLoaded', () => {
    add();
});


// 데이터 가져오기
 function add() {

    fetch('/movie/crawling')
            .then((response) => response.json())
            .then((data) => {
                            console.log(data);
                            if(data.length === 0){
                            	location.href="/login";
                            }
                           let randomNumber = Math.floor(Math.random() * 7);
                           console.log(randomNumber);
                           console.log(crawlingData[randomNumber].img);
                           poster.setAttribute('src', crawlingData[randomNumber].img);
            })

    /*$.ajax({
        url: 'crawling.do',
        type: 'get',
        success: function(data) {
        	crawlingData = setData(data);
            console.log(crawlingData);
            if(crawlingData.length === 0){
            	location.href="login";
            }
            let randomNumber = Math.floor(Math.random() * 7);
            console.log(randomNumber);
            console.log(crawlingData[randomNumber].img);
            poster.setAttribute('src', crawlingData[randomNumber].img);
        },
    });*/
}

function setData(data) {
    return JSON.parse(data);
}

// 로그인 버튼 클릭시에 동작하는 함수

loginButton.addEventListener('click', () => {
    if (id.value.trim() === '') {
    	toastr.error('아이디를 입력해 주십시오', '경고', {
            timeOut: 3000,
        });
        id.focus();
        return;
    } else if (pwd.value.trim() === '') {
    	toastr.error('비밀번호를 입력해 주십시오', '경고', {
            timeOut: 3000,
        });
        pwd.focus();
        return;
    }

    fetch("/login", {
      method: "POST",
      headers: {
          "Content-Type": "application/json",
        },
      body: JSON.stringify({
        userId: id.value,
        pwd: pwd.value,
      }),
    })
    .then((response) => response.json())
    .then((data) => {
                    alert(data.message);
                    if(data.status === "200"){
                       location.href = "/";
                    }
    })
    .catch(err => console.log("err: ", err))

});

// 회원가입 창으로 이동
moveRegister.addEventListener('click', () => {
    location.href = 'register';
});

// 메인 홈페이지로 이동
moveMain.addEventListener('click', () => {
    location.href = '/';
});

// 쿠키 처리 부분
/*let userId = $.cookie('userId');*/
/*document.cookie = "userId=userId";*/
let userId = getCookie('userId');
if (userId != null) {
    // 지정한 쿠키가 있을 때
    // alert("쿠키 있음");
    // $('#id').val(user_id);
    id.value = userId;
    // $('#chk_save_id').attr('checked', 'checked');
    checkSaveId.setAttribute('checked', 'checked');
    var date = new Date();
    date.setTime(date.getTime() + exp*24*60*60*1000);
    document.cookie = userId + '=' + userId + ';expires=' + date.toUTCString() + ';path=/';
}
//쿠키저장
/*var setCookie = function(userId, userId, 1) {

};*/


// 쿠키삭제
var deleteCookie = function(userId) {
          document.cookie = userId + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
      }

checkSaveId.addEventListener('click', () => {
    if (checkSaveId.checked === true) {
        if (id.value.trim() === '') {
            alert('id를 입력해 주십시오');
            // $('#chk_save_id').prop('checked', false);
            checkSaveId.checked = false;
        } else {
            // 정상 기입한 경우
            // 쿠키 저장
            /*$.cookie('userId', id.value.trim(), {
                expires: 7,
                path: './',
            });*/
            setCookie("userId", id.value.trim(), 1);
            /*setCookie('userId', id.value.trim(), '3');*/
        }
    } else {
        // alert("체크 없어짐");
        /*$.removeCookie('userId', {
            path: './',
        });*/

         deleteCookie('userId');
    }
});