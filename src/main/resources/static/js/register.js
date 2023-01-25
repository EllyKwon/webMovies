let crawlingData = [];
const idCheckButton = document.querySelector('.idCheckButton');
const registerButton = document.querySelector('.registerButton');
const id = document.querySelector('#userId');
const registerForm = document.querySelector('#registerForm');
const password = document.querySelector('#registerForm #pwd');
const passwordRepeat = document.querySelector('#registerForm #passwordRepeat');
const poster = document.querySelector('#poster');
let idFlag = false;

document.addEventListener('DOMContentLoaded', () => {
    idCheckButton.addEventListener('click', function() {
        idCheck();
    });
    add();

    registerButton.addEventListener('click', () => {
        registerForm.submit();
    });
});

function idCheck() {
    fetch('/idCheck?id=' + document.querySelector('#userId').value)
      .then((response) => response.json())
      .then((data) => {
          idCheckMessage(data);
      })
      .catch(err => console.log("err: ", err))
}

function add() {
    fetch('/movie/crawling')
        .then((response) => response.json())
        .then((data) => {
           let randomNumber = Math.floor(Math.random() * 7);
           poster.setAttribute('src', data[randomNumber].img);
        });
}

function idCheckMessage(data) {
    if (id.value.length < 4) {
        Swal.fire({
             text: '최소 4글자 이상의 아이디를 입력해 주세요',
             confirmButtonText: '아이디 확인',
             width: 270
        })
        return;
    }

    if (data === false) {
        Swal.fire({
             text: '사용할수 있는 아이디입니다',
             confirmButtonText: '아이디 확인',
             width: 270
        })
        return idFlag = true;
    } else {
         Swal.fire({
             text: '이미 존재하는 아이디입니다',
             confirmButtonText: '아이디 확인',
             width: 270
         })
        return idFlag = false;
        //registerButton.setAttribute('disabled', 'true');
    }
}

// register.html의 onChange함수에 넣었다.
function passwordValidate() {
    if (password.value == passwordRepeat.value) {
        Swal.fire({
             text: '비밀번호가 일치합니다',
             confirmButtonText: '비밀번호 확인',
             width: 270
        })

        if(idFlag === true){
        	registerButton.removeAttribute('disabled');
            registerButton.classList.toggle('clickedButton', false);
        }
        return true;
    } else {

        registerButton.classList.toggle('clickedButton', true);
        Swal.fire({
             text: '비밀번호가 일치하지 않습니다',
             confirmButtonText: '비밀번호 확인',
             width: 270
        })

        return false;
    }
}


