getAllEmployees();

// Add employee function
function addEmployee() {
  let name = $("#exampleFormControlInput2").val();
  let address = $("#exampleFormControlInput3").val();
  let contactNumber = $("#exampleFormControlInput4").val();

  $.ajax({
    method: "POST",
    contentType: "application/json",
    url: "http://localhost:8080/api/v1/employee/addEmployee",
    async: true,
    data: JSON.stringify({
      empID: "",
      empName: name,
      empAddress: address,
      empContactNumber: contactNumber,
    }),
    success: function (data) {
      alert("Employee Added Successfully");
      getAllEmployees();
    },
    error: function (xhr, exception) {
      alert("Employee Add Failed");
    },
  });
}

// Update employee function
function updateEmployee() {
  let empID = $("#exampleFormControlInput1").val();
  let name = $("#exampleFormControlInput2").val();
  let address = $("#exampleFormControlInput3").val();
  let contactNumber = $("#exampleFormControlInput4").val();

  $.ajax({
    method: "PUT",
    contentType: "application/json",
    url: "http://localhost:8080/api/v1/employee/updateEmployee",
    async: true,
    data: JSON.stringify({
      empID: empID,
      empName: name,
      empAddress: address,
      empContactNumber: contactNumber,
    }),
    success: function (data) {
      alert("Employee Update Successfully");
      getAllEmployees();
    },
    error: function (xhr, exception) {
      alert("Employee Update Failed");
    },
  });
}

// Delete employee function
function deleteEmployee() {
  let empID = $("#exampleFormControlInput1").val();

  $.ajax({
    method: "DELETE",
    url: "http://localhost:8080/api/v1/employee/deleteEmployee/" + empID,
    async: true,
    success: function (data) {
      alert("Employee Delete Successfully");
      getAllEmployees();
    },
    error: function (xhr, exception) {
      alert("Employee Delete Failed");
    },
  });
}

// Get all employees function
function getAllEmployees() {
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/api/v1/employee/getallEmployee",
    async: true,
    success: function (data) {
      if (data.code === "00") {
        $("#empTable").empty();
        for (let emp of data.content) {
          let empID = emp.empID;
          let name = emp.empName;
          let address = emp.empAddress;
          let contactNumber = emp.empContactNumber;
          var row = `<tr>
            <td>${empID}</td>
            <td>${name}</td>
            <td>${address}</td>
            <td>${contactNumber}</td>
          </tr>`;
          $("#empTable").append(row);
        }
      }
    },
    error: function (xhr, exception) {
      alert("Employee Fetch Failed");
    },
  });
}

$(document).ready(function () {
  $(document).on("click", "#empTable tr", function () {
    var col0 = $(this).find("td:eq(0)").text();
    var col1 = $(this).find("td:eq(1)").text();
    var col2 = $(this).find("td:eq(2)").text();
    var col3 = $(this).find("td:eq(3)").text();

    $("#exampleFormControlInput1").val(col0);
    $("#exampleFormControlInput2").val(col1);
    $("#exampleFormControlInput3").val(col2);
    $("#exampleFormControlInput4").val(col3);
  });
});
