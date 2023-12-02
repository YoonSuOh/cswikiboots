/* EXPANDER MENU */
const showMenu = (toggleId, navbarId, bodyId) => {
    const toggle = document.getElementById(toggleId),
        navbar = document.getElementById(navbarId),
        bodypadding = document.getElementById(bodyId)

    if( toggle && navbar ) {
        toggle.addEventListener('click', ()=>{
            navbar.classList.toggle('expander');

            // bodypadding.classList.toggle('body-pd')
        })
    }
}

showMenu('nav-toggle', 'navbar', 'body-pd')

/* LINK ACTIVE */
const linkColor = document.querySelectorAll('.nav__link')
function colorLink() {
    linkColor.forEach(l=> l.classList.remove('active'))
    this.classList.add('active')
}
linkColor.forEach(l=> l.addEventListener('click', colorLink))

/* COLLAPSE MENU */
const linkCollapse = document.getElementsByClassName('collapse__link')
var i

for(i=0;i<linkCollapse.length;i++) {
    linkCollapse[i].addEventListener('click', function(){
        const collapseMenu = this.nextElementSibling
        collapseMenu.classList.toggle('showCollapse')

        const rotate = collapseMenu.previousElementSibling
        rotate.classList.toggle('rotate')
    });
}

var toggleButtons = document.querySelectorAll('.category-toggle');

toggleButtons.forEach(function(button) {
    button.addEventListener('click', function() {
        var parentUl = button.parentElement.querySelector('ul');
        if (parentUl) {
            if (parentUl.classList.contains('hidden')) {
                parentUl.classList.remove('hidden');
            } else {
                parentUl.classList.add('hidden');
            }
        }

        var idElement = button.parentElement.querySelector('#p');
        if (idElement) {
            // 선택한 카테고리의 id 값을 가져와서 parent_id 필드에 할당
            var parentIdInput = button.parentElement.querySelector('input[name="parent_id"]');
            parentIdInput.value = idElement.textContent;
        }
    });
});


/* 카테고리 수정 삭제 */
$(document).ready(function(){

    $('.category-toggle').on('dblclick', function(){
        let id = $(this).data("id");
        let name = $(this).text();
        console.log(id);
        console.log(name);
        $('#edit').css("display", "block");
        $('input[name="edit-category"]').val(name);

        $('#editBtn').on('click', function(e){
            e.preventDefault();
            let name = $('input[name="edit-category"]').val();
            $.ajax({
                type: 'put'
                ,url: '/doc/categoryEdit'
                ,data:{"id":id, "name":name}
                , success : function(data){
                    if(data.code == 200){
                        alert('카테고리가 수정되었습니다!');
                        location.reload(true);
                    } else {
                        alert('서버 오류가 발생했습니다.')
                    }
                }
                ,error : function(){
                    alert('카테고리 수정에 실패했습니다.');
                }
            });
        });
        $('#delBtn').on('click', function(e){
            e.preventDefault();
            if(confirm("정말 카테고리를 삭제하시겠습니까? 하위 카테고리도 모두 삭제됩니다.")){
                $.ajax({
                    type: 'delete'
                    ,url: '/doc/categoryDelete'
                    ,data:{"id":id}
                    , success : function(data){
                        if(data.code == 200){
                            alert('카테고리가 삭제되었습니다!');
                            location.reload(true);
                        } else {
                            alert('서버 오류가 발생했습니다.')
                        }
                    }
                    ,error : function(){
                        alert('카테고리 삭제에 실패했습니다.');
                    }
                });
            } else {
                return false;
            }
        });
    });
});

var bDisplay = true; function doDisplay(){
    var con = document.getElementById("nav__list");
    if(con.style.display=='block'){
        con.style.display = 'none';
    }else{
        con.style.display = 'block';
    }
}

$(document).on('click', function(e) {
    var container = $("#edit");
    if (!$(e.target).closest(container).length) {
        container.hide();
    }
});