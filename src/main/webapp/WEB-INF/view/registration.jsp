<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<html>
<head>
    <title>ȸ������ ȭ��</title>

    <!-- css ���� �и� -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript">

        // �ʼ� �Է������� ���̵�, ��й�ȣ�� �ԷµǾ����� Ȯ���ϴ� �Լ�
        function checkValue()
        {
            if(!document.userInfo.name.value){
                alert("���̵� �Է��ϼ���.");
                return false;
            }

            if(!document.userInfo.password.value){
                alert("��й�ȣ�� �Է��ϼ���.");
                return false;
            }

            // ��й�ȣ�� ��й�ȣ Ȯ�ο� �Էµ� ���� �������� Ȯ��
            if(document.userInfo.password.value != document.userInfo.passwordcheck.value ){
                alert("��й�ȣ�� �����ϰ� �Է��ϼ���.");
                return false;
            }
        }

        // ��� ��ư Ŭ���� �α��� ȭ������ �̵�
        function goLoginForm() {
            location.href="LoginForm.jsp";
        }
    </script>

</head>
<body>

<!-- div ����, ������ �ٱ������� auto�� �ָ� �߾����ĵȴ�.  -->
<div id="wrap">
    <br><br>
    <b><font size="6" color="gray">ȸ������</font></b>
    <br><br><br>


    <!-- �Է��� ���� �����ϱ� ���� form �±׸� ����Ѵ� -->
    <!-- ��(�Ķ����) ������ POST ���, ������ �������� JoinPro.jsp -->
    <form method="get" action="/"  name="userInfo" id="userInfo">
        <table>
            <tr>
                <td id="title">���̵�</td>
                <td>
                    <input type="text" name="name" id="name" maxlength="50">
                    <input type="button" value="�ߺ�Ȯ��" id="searchId">
                </td>
            </tr>

            <tr>
                <td id="title">��й�ȣ</td>
                <td>
                    <input type="password" name="password" maxlength="50">
                </td>
            </tr>

            <tr>
                <td id="title">��й�ȣ Ȯ��</td>
                <td>
                    <input type="password" name="passwordcheck" maxlength="50">
                </td>
            </tr>

        </table>
        <br>
        <input type="button" value="����" id="set" class="no" onclick="return checkValue()"/>
        <a href="/" type="button">���</a>
    </form>
</div>
<script type="text/javascript">

    $('#searchId').click(function () {
        var url = '/findUser/' + document.getElementById("name").value
        $.ajax({
            type: "GET",                               //1
            url: url,                          //2
            dataType: 'json',                           //3
            contentType: 'application/json',            //4
            data: "",                 //5
            complete: function (resp) {
                console.log(resp)
                if (resp.responseText == "ok") {
                    alert("���̵� ����Ҽ� �ֽ��ϴ�.")
                    document.getElementById("set").className="ok"
                    return false;
                } else {
                    alert("���̵� ����Ҽ� �����ϴ�.")
                    return false;
                }

            },                       //6             //7
        });
    });

    $('#set').click(function (){
        if(document.getElementById("set").className=="ok"){
            var user={
                "name":document.userInfo.name.value,
                "password":document.userInfo.password.value,
                "admin":"NoN"
            }
            $.ajax({
                type : "POST",                               //1
                url : "/registration",                          //2
                dataType : 'json',                           //3
                contentType : 'application/json',            //4
                data :JSON.stringify(user),                 //5
                complete : function (resp){
                    console.log(resp)
                    if(resp.responseText=="ok"){
                        alert("���Կ� �����Ͽ����ϴ�.")
                        document.getElementById("userInfo").submit();
                        return false;
                    }
                    else{
                        alert("���Կ� �����Ͽ����ϴ�.")
                        return false;
                    }

                },                       //6             //7
            });
        }

    });

</script>
</body>
</html>
