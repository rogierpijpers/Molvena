$(document).ready(function() {
           console.log("ready");
                       $('#table_id').DataTable({
                           "processing" : true,
                           "ajax" : {
                               "url" : "http://localhost:8080/room/",
                               dataSrc : '',
                               beforeSend: function (xhr) {
                                   xhr.setRequestHeader ("Authorization", "Basic " + btoa("Henk@vanvliet.nl" + ":" + "secret123"));
                               }
                           },
                           "columns" : [ {
                               "data" : "roomID"
                           }, {
                               "data" : "roomType"
                           }
                           ]
                       });
                   });