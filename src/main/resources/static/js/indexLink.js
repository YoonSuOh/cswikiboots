// 목차를 나열하는 div태그를 변수에 지정
let indexArea = document.getElementById('doc_index');
// content 클래스 하위의 h1 태그 모음 지정
let h1sLength = Array.from(document.querySelectorAll(".content h1"));
// h1 태그의 텍스트 값을 담는 배열
let h1sValueArray = new Array();

for (let i = 0; i < h1sLength.length; i++) {
    // 1번째 h1태그부터 마지막 h1태그의 텍스트 값 받기
    let h1sTextValue = h1sLength[i].textContent;
    h1sValueArray[i] = h1sTextValue;
}

// span 태그 동적 생성 -> 내부에 a 태그 추가
for (let i = 0; i < h1sValueArray.length; i++) {
    const newIndex = document.createElement('span');
    indexArea.appendChild(newIndex);
    let indexLink = document.createElement('a');
    indexLink.setAttribute('href', `#index${i+1}`);
    indexLink.append(i + ". ");
    indexLink.append(h1sValueArray[i]);
    newIndex.appendChild(indexLink);
}



// content 클래스 하위 h1태그 지정
const h1s = document.querySelectorAll(".content h1");
for (let i = 0; i < h1s.length; i++) {
    h1s[i].id = `index${i+1}`;
}