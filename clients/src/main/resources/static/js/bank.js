$(document).ready(function(){

    $('.notification-locForm').hide();
    $(".lc-app-list").hide();
    $(".lc-from-response").hide();
	$('.internet-lc-issue').click(function(){
	$(".lc-app-list").show();

  });

  $('#creditAvailableWith').on('change',function() {
      var caw = $(this).val();

        if(caw=="Negotiation restricted to"){
            $(".negotiationSelect").show();
        }else{
            $(".negotiationSelect").css("display","none");
        }
  });



  $(function () {
      $("#lcIssueDate").datepicker();
	  $("#lcExpiryDate").datepicker();
	  $("#shipmentDate").datepicker();
  });

      $('.fa-sign-out').on('click', function (e) {
              $.ajax({
                      url: '/logout',
                      type: 'GET',
                      contentType: 'application/json',
                      async: false,
                      success: function(data){
                        sessionStorage.setItem("username", "null");
                        location.href= "/login";
                      },
                      error: function(e){
                          console.log( e );

                      }
                  });
            });

        $(".internet-lc-issue").click(function(){
             var response ;
             $(".table-striped").show();
             $('.notification-locForm').hide();
             $('.notification-details').hide();
             $(".lc-from-response").hide();
             $(".app-list-tbody").remove();
             showOpenLoCApplications("OPEN");
             $('.collapseDD').removeClass('show');

        });


        window.setInterval(function(){
               /// call your function here
              notificationCount("OPEN");
              notificationCount("APPROVED");
        }, 6000);

});

function showNotificationDetails(status){

          $('.table-striped').hide();
          $('.notification-details').hide();
          getLoCNotificationByStatus(status);
          $('.notification-locForm').show();


}

function cancelNotiDetails(){
    $('.table-striped').hide();
    $('.notification-details').hide();
    getLoCNotificationByStatus("APPROVED");
    $('.notification-locForm').show();
}

function showOpenLoCApplications(status){

            $.ajax({
                url: '/getLoCsByStatus/'+status,
                dataType: 'JSON',
                type: 'GET',
                contentType: 'application/json',
                async: false,

                success: function( data){
                    console.log(data);
                    response= data;
                },
                error: function(e){
                    console.log( e );
                }
            });

            $(".approve-locForm").hide();
            var trHTML = '<tbody class="app-list-tbody">';

            $.each(response, function (i, item) {
                trHTML +=
                       '<tr><td>' + item.lcNumber +
                       '</td><td> Inland LC issuance'+
                       '</td><td>' + item.applicant.name.organisation +
                       '</td><td>' + item.beneficiary.name.organisation +
                       '</td><td>' + item.currency +
                       '</td><td>' + item.amount +
                       '</td><td>' + item.locStatus +
                       '</td><td><span class="fa fa-eye" onclick="showDetails(event)"><br/><p style="display:none;">' +item.locID.id+
                                                    '</p></span></td></tr>';
            });
            $('#app-list-table').append(trHTML+'</tbody>');
}

function showDetails(event){

        var locID = $(event.target).text();
        var responseData;
                    $.ajax({
                        url: '/getLoCApplicationById/'+ locID,
                        type: 'GET',
                        contentType: 'application/json',
                        async: false,
                        processData: false,

                        success: function(data){
                        console.log(data);
                        responseData=data;
                        },
                        error: function(e){
                            console.log( e );
                        }
                    });

                    $.ajax({
                        url: '/getAproveLocView',
                        type: 'GET',
                        contentType: 'application/json',
                        async: false,
                        processData: false,

                        success: function(data){
                            console.log(data);
                            $("#app-list-table").hide();
                            $('.approve-locForm').empty();
                            $(".approve-locForm").append(data);
                            $(".approve-locForm").show();
                            $("#locID").val(locID);
                            $("#lcNumber").val(responseData.lcNumber);
                            $("#amount").val(responseData.amount);
                            $("#lcIssueDate").val(responseData.issueDate);
                            $("#lcExpiryDate").val(responseData.expiryDate);
                            $("#lcIssuePlace").val(responseData.expiryPlace);
                            $("#finalDestination").val(responseData.finalDestination);
                            $("#incoTerms").val(responseData.incoTerms);
                            $("#presentationDays").val(responseData.presentationDays);
                            $("#partialShipment").val(responseData.partialShipment);
                            $("#transhipmentShipment").val(responseData.transhipmentShipment);
                            $("#shipmentDate").val(responseData.shipmentDate);
                            $("#placeOfTakingInCharge").val(responseData.placeOfTakingInCharge);
                            $("#creditAmountTolerance").val(responseData.creditAmountTolerance);
                            $("#debitAmountTolerance").val(responseData.debitAmountTolerance);
                            $("#shipmentPeriod").val(responseData.shipmentPeriod);
                            $("#portofLoading").val(responseData.portOfLoading);
                            $("#portOfDischarge").val(responseData.portOfDischarge);
                            $("#advisingBankID").val(responseData.advisingBankName);
                            $("#advisingBankAddress").val(responseData.advisingBankAddress);
                            $("#creditAvailableBy").val(responseData.creditAvailableBy);
                            $("#creditAvailableWith").val(responseData.creditAvailableWith);

                            var caw = responseData.creditAvailableWith;
                                if(caw == "Negotiation restricted to"){
                                    $(".negotiationSelect").show();
                                }else{
                                  $(".negotiationSelect").css("display","none");
                                }

                            $("#bankName").val(responseData.bankName);
                            $("#bankAddress").val(responseData.bankAddress);
                            $("#iFSCCode").val(responseData.ifscCode);
                            if(responseData.chargesFrom =='None'){
                                $("#chargesNoneRadio").attr('checked', 'checked');
                            }
                            if(responseData.chargesFrom =='Opener'){
                                $("#chargesOpenerRadio").attr('checked', 'checked');
                            }
                            if(responseData.chargesFrom =='Beneficiary'){
                               $("#chargesBeneficiaryRadio").attr('checked', 'checked');
                            }
                            if(responseData.commissionPartyCharge =='None'){
                            $("#commissionNoneRadio").attr('checked', 'checked');
                            }
                            if(responseData.commissionPartyCharge =='Opener'){
                                $("#commissionOpenerRadio").attr('checked', 'checked');
                            }
                            if(responseData.commissionPartyCharge =='Beneficiary'){
                               $("#commissionBeneficiaryRadio").attr('checked', 'checked');
                            }
                            if(responseData.marginRequired=='on'){
                                 $("#marginRequired").attr('checked', 'checked');
                            }
                            $("#marginPercent").val(responseData.marginPercentage);
                            $("#marginDebitAccount").val(responseData.marginDebitAccount);
                            $("#marginAmount").val(responseData.marginAmount);
                            $("#marginCreditAccount").val(responseData.marginCreditAccount);
                            $("#descriptionofGoods").val(responseData.descOfGoods);
                            $("#documentsRequired").val(responseData.docsRequired);
                            $("#additionalConditions").val(responseData.additionalConditions);
                            $("#draft").val(responseData.draft);
                        },
                        error: function(e){
                            console.log( e );
                        }
                    });
}

function approveLoCApplication(event){

        $(".lc-from-response").empty();
        var data=Object();
        var locID = $('#locID').val();
        data.locID = locID;
        data.id= $('#lcNumber').val().substring(4);
        data.lcNumber = $('#lcNumber').val();
        data.beneficiary= "O=EXPORTER KENT Company,L=Hyderabad,C=IN";
        data.issuingBank= "O=TT Bank,L=Mumbai,C=IN";
        data.advisingBank= "O=BKC Bank,L=Hyderabad,C=IN";
        data.applicant = "O=IMPORTER ABC Company,L=Mumbai,C=IN";
        data.currency="INR";
        data.amount=$('#amount').val();
        data.locStatus="OPEN";
        data.debitAmountTolerance= $('#debitAmountTolerance').val();
        data.creditAmountTolerance=$('#creditAmountTolerance').val();
        data.issueDate = $('#lcIssueDate').val();
        data.expiryDate = $('#lcExpiryDate').val();
        data.expiryPlace = $('#lcIssuePlace').val();
        data.presentationDays = $('#presentationDays').val();
        data.shipmentDate = $('#shipmentDate').val();
        data.shipmentPeriod = $('#shipmentPeriod').val();
        data.incoTerms = $('#incoTerms').val();
        data.partialShipment = $('#partialShipment').val();
        data.transhipmentShipment = $("#transhipmentShipment").val();
        data.portOfLoading = $("#portofLoading").val();
        data.portOfDischarge = $("#portOfDischarge").val();
        data.placeOfTakingInCharge = $("#placeOfTakingInCharge").val();
        data.finalDestination=	 $("#finalDestination").val();
        data.creditAvailableBy= $("#creditAvailableBy").val();
        data.creditAvailableWith= $("#creditAvailableWith").val();
        data.applicationCreationDate="";
        data.lcType="";
        data.applicationRuleCode=$("#applicationRuleCode").val();
        data.appRuleDesc=$("#appRuleDesc").val();
        data.applicantID=$("#applicantID").val();
        data.applicantAddress=$("#applicantAddress").val();
        data.beneficiaryName = $("#beneficiaryName").val();
        data.beneficiaryAddress = $("#beneficiaryAddress").val();
        data.issuingBankName = "tt";//$("#issuingBankName").val();
        data.issuingBankAddress ="ttt";// $("#issuingBankAddress").val();
        data.advisingBankName = $("#advisingBankID").val();
        data.advisingBankAddress = $("#advisingBankAddress").val();
        data.bankName = $("#bankName").val();
        data.bankAddress = $("#bankAddress").val();
       data.ifscCode = $("#iFSCCode").val();
       //charges
       var chargeForm = $('input[name=chargesFormRadio]:checked').val();
       if(chargeForm != undefined){
            data.chargesFrom = chargeForm;
       }else{
            data.chargesFrom = "";
       }
       data.chargesDefaultAccount = "";
       data.waiveCharges = "";
       data.chargeCodeOne = "";
       data.chargeDebitAccountNum = "";
       data.chargeCurrency = "";
       data.chargeAmount = "";
       //commission
       data.commissionCode = "";
       data.waiveCommissionFlag = "";
       var commPartyCharge = $('input[name=commissionPartyRadio]:checked').val();
       if(commPartyCharge != undefined){
           data.commissionPartyCharge = commPartyCharge;
       }else{
            data.commissionPartyCharge = "";
       }
       data.commissionCurrency = "";
       data.commissionAmount = "";
       data.commissionAccount = "";
       //margin
       var marginReq = $('input[name=marginRequired]:checked').val();
       if(marginReq != undefined){
            data.marginRequired = marginReq;
       }else{
         data.marginRequired = "";
       }
       data.marginCalcBase = "";
       data.marginPercentage = $("#marginPercent").val();
       data.marginDebitAccount = $("#marginDebitAccount").val();
       data.marginAmount = $("#marginAmount").val();
       data.marginCreditAccount = $("#marginCreditAccount").val();
       //Documents & Conditions
       data.draft = $('#draft').val();
       data.poPiContractRegNum = "";//$("#poPIContractIndent").val();
       data.poAmount = $("#poAmount").val();
       data.poDate = "";//$("#poDate").val();
       data.descOfGoods = $("#descriptionofGoods").val();
       data.docsRequired = $("#documentsRequired").val();
       data.additionalConditions = $("#additionalConditions").val();
       $('.lcSuccessMsg').empty();
        console.log(data);

        $.ajax({
                url: '/approveLoC/'+locID,
                dataType: 'JSON',
                type: 'POST',
                contentType: 'application/json',
                async: false,
                data: JSON.stringify(data),
                processData: false,

                success: function( data){
                console.log(data);
                        if(data.status == 'Success'){
                                $(".approve-locForm").hide();
                                $(".lc-from-response").show();
                                 $('.lcSuccessMsg').append('<h3>Successfully approved the LC</h3>');
                                 $('#lcModal').modal('show');

                                  event.preventDefault();
                          }else{
                               $('.lcSuccessMsg').append('<h3>'+data.error+'</h3>');
                               $('#lcModal').modal('show');
                          }
                },
                error: function(e){
                    console.log( e );
                }
            });
            $(".lc-from-response").show();
}


function getLoCNotificationByStatus(status){
    $(".lc-from-response").hide();
    $(".approve-locForm").hide();
      var response;
        $.ajax({
                url: '/getLoCsByStatus/'+status,
                dataType: 'JSON',
                type: 'GET',
                contentType: 'application/json',
                async: false,

                success: function( data){
                    console.log(data);
                    response=data;
                },
                error: function(e){
                    console.log( e );

                }
            });
             var trHTML = '<div id="page-content-wrapper" style="display:contents;">'+
             '<div class="container-fluid" style="padding: 15px">'+
             '<div class="container" style="padding-top: 15px">';

             $.each(response, function (i, item) {
             var operationType="";
              if(item.locStatus == "APPROVED"){
                  operationType= "Issue Inland LC"
              }
                trHTML +=
                                '<div class="card"><div class="card-body">'+
                                                     '<p calss="card-text">Operation Type   :'+ operationType+
                                                     '</p><p class="card-text">Importer Name  :'+ item.applicant.name.organisation +
                                                     '</p><p class="card-text">Exporter Name  :'+ item.beneficiary.name.organisation +
                                                     '</p><p class="card-text">LC Number  :'+ item.lcNumber +
                                                     '</p><button class="btn btn-primary" style="margin-left:350px" onclick="notificationDetailsView(event)">View<p style="display:none;">' +item.locID.id+
                                                      '</p></button></div></div>';
             });
             trHTML= trHTML+ '</div></div></div>';
             $(".notification-locForm").html(trHTML);


}

function notificationDetailsView(event){
         $('.notification-locForm').hide();
         $('.notification-details').empty();

         var locID = $(event.target).text().substring(4);

         $('.notification-details').show();
          var responseData;
                $.ajax({
                        url: '/getLoCApplicationById/'+ locID,
                        type: 'GET',
                        contentType: 'application/json',
                        async: false,
                        processData: false,

                        success: function(data){
                        console.log(data);
                        responseData=data;
                        },
                        error: function(e){
                            console.log( e );
                        }
                    });
                      $.ajax({
                              url: '/getNotificationDetailView',
                              type: 'GET',
                              contentType: 'application/json',
                              async: false,
                              processData: false,

                              success: function(data){
                                  console.log(data);

                              $(".notification-details").append(data);
                              $(".adviceButton").show();
                              $("#locID").val(locID);
                              $("#lcNumber").text(responseData.lcNumber);
                              $("#currency").text(responseData.currency);
                              $("#amount").text(responseData.amount);
                              $("#lcIssueDate").text(responseData.issueDate);
                              $("#lcExpiryDate").text(responseData.expiryDate);
                              $("#lcIssuePlace").text(responseData.expiryPlace);
                              $("#finalDestination").text(responseData.finalDestination);
                              $("#presentationDays").text(responseData.presentationDays);
                              $("#creditAvailableWith").text(responseData.creditAvailableWith);
                              $("#partialShipment").text(responseData.partialShipment);
                              $("#transhipmentShipment").text(responseData.transhipmentShipment);
                              $("#shipmentDate").text(responseData.shipmentDate);
                              $("#placeOfTakingInCharge").text(responseData.placeOfTakingInCharge);
                              $("#descriptionofGoods").text(responseData.descOfGoods);
                              $("#documentsRequired").text(responseData.docsRequired);
                              $("#applicationRuleCode").text(responseData.applicationRuleCode);
                              $("#appRuleDesc").text(responseData.appRuleDesc);
                              $("#applicantID").text(responseData.applicantID);
                              $("#beneficiaryName").text(responseData.beneficiaryName);
                              $("#draft").text(responseData.draft);
                              $('#shipmentPeriod').text(responseData.shipmentPeriod);
                              $("#issuingBankName").text(responseData.issuingBankName);
                              },
                              error: function(e){
                                  console.log( e );
                              }
                          });

      }


  function processLoCApplication(event){
      $(".lc-from-response").empty();

        var locID = $("#locID").val();
        console.log(locID);
       $.ajax({
                  url: '/adviceLoC/'+ locID,
                  type: 'POST',
                  contentType: 'application/json',
                  async: false,
                  processData: false,

                  success: function(data){
                  console.log(data);
                  $('.lcSuccessMsg').empty();
                  if(data.status == 'Success'){

                        $(".notification-details").hide();
                        $('.lcSuccessMsg').append('<h3>Successfully advised to exporter</h3>');
                        $('#lcModal').modal('show');

                        event.preventDefault();
                  }else{
                       $(".lc-from-response").append('<h3>'+data.error+'</h3>');
                       $(".lc-from-response").show();
                  }

                  },
                  error: function(e){
                      console.log( e );
                  }
              });

      }

      function sendForAlteration(){
              var data= Object();
              var alterDetails = $('#alterDetails').val();
              var locID = $('#locID').val();
              console.log(locID);
              console.log(alterDetails);

              data.comment = alterDetails;
              data.date = new Date().toString();
              console.log(data.date);
                 $.ajax({
                                  url: '/alterLoC/'+ locID,
                                  dataType: 'JSON',
                                  type: 'POST',
                                  contentType: 'application/json',
                                  async: false,
                                  data: JSON.stringify(data),
                                  processData: false,

                                  success: function(data){
                                  console.log(data);
                                  $('.lcSuccessMsg').empty();
                                      $(".approve-locForm").hide();
                                      $(".lc-from-response").show();
                                       $('.lcSuccessMsg').append('<h3>Successfully altered the LC</h3>');
                                       $('#lcModal').modal('show');

                                        event.preventDefault();
                                  },
                                  error: function(e){
                                      console.log( e );
                                  }
                              });


      }

      function notificationCount(status){
                  var i=0;
                  var j=0;
                  $.ajax({
                              url: '/getLoCCountByStatus/'+status,
                          //    dataType: 'JSON',
                              type: 'GET',
                              contentType: 'application/json',
                              async: false,
                          //    data: JSON.stringify(data),
                              processData: false,

                              success: function( data){
                              if(status == 'OPEN'){
                                  i=data;
                              }
                              if(status == 'APPROVED'){
                                    j=data;
                                }

                               //   console.log(data);
                              },
                              error: function(e){
                                  console.log( e );

                              }
                          });

                          if(status == 'OPEN'){
                              $(".badgeinfo").addClass("sec");
                              if(i == 0){
                                  $(".badgeinfo").removeClass("sec");
                              }
                          }

                          if(status == 'APPROVED'){
                                $(".badgeinfo").addClass("sec1");
                                if(j == 0){
                                    $(".badgeinfo").removeClass("sec1");
                                }
                            }

              }