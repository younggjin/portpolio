function join_btn() {
        if(fm.userid.value == "") {
            alert('아이디를 입력해주세요');
            fm.userid.focus();
            return false;
        }
        if(fm.password.value == "") {
            alert('비밀번호를 입력해주세요');
            fm.password.focus();
            return false;
        }
        if(fm.passwordch.value == "") {
            alert('비밀번호확인 입력해주세요');
            fm.passwordch.focus();
            return false;
        }
        if(fm.password.value != fm.passwordch.value) {
            alert('비밀번호가 일치하지 않습니다. 다시 입력해주세요');
            fm.passwordch.focus();
            return false;
        }
        if(fm.name.value == "") {
            alert('이름을 입력해주세요');
            fm.name.focus();
            return false;
        }
        if(fm.add1.value == "") {
            alert('주소를 입력해주세요');
            fm.add1.focus();
            return false;
        }
        if(fm.add3.value == "") {
            alert('주소를 입력해주세요');
            fm.add3.focus();
            return false;
        }
        if(fm.mail.value == "") {
            alert('이메일을 입력해주세요');
            fm.mail.focus();
            return false;
        }
        if(fm.tel1.value == "") {
            alert('번호를 입력해주세요');
            fm.tel1.focus();
            return false;
        }
        if(fm.tel2.value == "") {
            alert('번호를 입력해주세요');
            fm.tel2.focus();
            return false;
        }
        if(fm.tel3.value == "") {
            alert('번호를 입력해주세요');
            fm.tel3.focus();
            return false;
        }
}