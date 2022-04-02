

var Main_Form 		= "#Main_Form";
var Main_Form_BG 		= "#Main_Form_bg";
var MAP_CANVAS	 		= "#map-canvas";
var TAB_BODY			= "#Main_Form .body";
var TAB_BUTTON			= TAB_BODY + " .side_nav .side_button";
var TAB_CONTENT			= TAB_BODY + " .content";
var TAB_SELECTED		= ".selected";
var TAB_CONTENT_WIDTH;
var ON_DRAG = false;

var BRANCHES_CONST = "Branches";
var LOGS_CONST	   = "logs";
var SYSETTING_CONST= "Setting";

$(window).ready(function(){
		$(Main_Form).css("display","none");
		$(Main_Form).draggable({ containment: Main_Form_BG});
		TAB_CONTENT_WIDTH = $(Main_Form).width();

		$("body").find(MAP_CANVAS).each(function(){
			$(Main_Form).draggable({ containment: $(this) , handle : $("#Main_Form h1") });
		});
		
		$( "body"  ).keyup(function( e ) {
			if(e.which == 27){
				$(Main_Form).css("display","none");
				$(Main_Form_BG).css("display","none");
			}
		});
		
		$(".side_button#close").click(function(){
			$(Main_Form).css("display","none");
			$(Main_Form_BG).css("display","none");
		});
		
		$(TAB_BUTTON).click(function(){
			showTab ( $(this) );
		});
		




});

function OnPageSelect(){
	var e = document.getElementById("page_selector");
	var val = e.options[e.selectedIndex].value;
	load_content("interface/uploads.php?page=" + val, $(TAB_CONTENT));
}

function showTab( tab , force)
{
			if( !tab.hasClass("selected") || force){
				$(TAB_BUTTON + TAB_SELECTED).removeClass("selected");
				tab.addClass("selected");
				
				var tab_name = tab.attr('id');
				$(TAB_CONTENT +"> div").hide();
				
				var php_file = tab.attr('php');
				load_content(php_file, $(TAB_CONTENT));
				
				$(Main_Form).width(TAB_CONTENT_WIDTH);
				$(Main_Form).find("h1").empty().append( tab.attr("title") );
			}
}

function OnEdit( BRANCH_CODE ){
		$(Main_Form).find("h1").empty().append( "<a href=javascript:view_Main_Form(BRANCHES_CONST,true)>Branches</a> > " + BRANCH_CODE );
		load_content("interface/branchInfo.php?BranchCode=" + BRANCH_CODE + "&EditMode=1", $(TAB_CONTENT));
}

function OnReset(){
		$input = $("#master_data").find("input");
		$input.each(function(){
		$(this).val( $(this).attr("initial-val") );
		});
}


function OnBack(){
	$(Main_Form).find("h1").empty().append( "Branches" );
	load_content("interface/branches.php", $(TAB_CONTENT));
}

function OnSave(){
	if( $("input[name='branchcode']").val().trim() == ""){
		alert("Please input a branch Code!");
		$("input[name='branchcode']").focus();
		return;
	}
	
	if( $("input[name='branchdesc']").val().trim() == ""){
		alert("Please input a Branch Name!");
		$("input[name='branchdesc']").focus();
		return;
	}
	
	if( $("input[name='branchpasscode']").val().trim() == ""){
		alert("Please input a branch Passcode!");
		$("input[name='branchpasscode']").focus();
		return;
	}

	var data = { 
		BranchUpdateType:  $("input[name='branchcode']").is('[readonly]') ? "Update" : "New",
		BranchCode 		:  $("input[name='branchcode']").val().trim(),
		BranchDesc 		:  $("input[name='branchdesc']").val().trim(),
		BranchPass 		:  $("input[name='branchpasscode']").val().trim(),
		BranchContact	:  $("input[name='branchcontactno']").val().trim(),
		BranchEmail		:  $("input[name='branchemail']").val().trim(),
		BranchAddr1     :  $("input[name='branchaddr1']").val().trim(),
		BranchAddr2	    :  $("input[name='branchaddr2']").val().trim(),
		BranchAddr3     :  $("input[name='branchaddr3']").val().trim(),
		BranchAddr4     :  $("input[name='branchaddr4']").val().trim()
	};
	load_content("interface/BranchUpdate.php", $(TAB_CONTENT),data);
}

function load_content(php_file,append,data)
{
		
	$.ajax({
		type: "POST",
        url: php_file,
		data: (data !== undefined ? data :  {}),
        beforeSend: function() {
            append.html("<div class='loading' style='height:400px;'></div>");
        },
        success: function(data) {
            append.html(data);
			
				
	
			if( $(MAP_CANVAS).height() <  $(Main_Form).height())
			{
				$(MAP_CANVAS).css("height", $(Main_Form).height() + $(Main_Form).position().top);
			
				$("body").find(MAP_CANVAS).each(function(){
					reinitialize_map();
				});
			}
			
			var n = $("div.slider").find(".button").length;
			
			
			$("div.slider").find(".button").each(function(){
				$(this).width($(this).parent().width() / n);
				
				$(this).click(function(){
					$(this).siblings().each(function() { 
								$(this).removeClass("selected");
								$($(this).attr("content")).hide();
					});
						
					$(this).addClass("selected");
					$($(this).attr("content")).fadeIn(100);
					
				});
				
				
				if( $(this).hasClass("selected") )
				{
					$(this).siblings().each(function() { 
						$(this).removeClass("selected");
						$($(this).attr("content")).hide();
					});
					$(this).addClass("selected");
					$($(this).attr("content")).fadeIn(100);
				}
			});
        }
    });
}

function view_branch( BRANCH_CODE )
{
	if(BRANCH_CODE !== undefined){
		$(Main_Form).find("h1").empty().append( "<a href=javascript:view_Main_Form(BRANCHES_CONST,true)>Branches</a> > " + BRANCH_CODE );
		load_content("interface/branchInfo.php?BranchCode=" + BRANCH_CODE, $(TAB_CONTENT));
	}
	else
	{
		$(Main_Form).find("h1").empty().append( "<a href=javascript:view_Main_Form(BRANCHES_CONST,true)>Branches</a> > Add New Branch" );
		load_content("interface/branchInfo.php", $(TAB_CONTENT));
	}
	

}


function remove_branch( BRANCH_CODE ){
	var data = { 
		BranchUpdateType:  "Delete",
		BranchCode 		:  BRANCH_CODE};
	
	load_content("interface/BranchUpdate.php", $(TAB_CONTENT),data);
}

function view_Main_Form(TAB_CONST,force)
{
	$(TAB_BUTTON + "#" + TAB_CONST).each(function(){	
		showTab( $(this) , force);
	});
	
	
	$(Main_Form_BG).css("display","block");
	
	
	$(Main_Form).css("left",  ($("body").width()/2) - ($(Main_Form).width()/2));
	$(Main_Form).css("top",  70);
	$(Main_Form).css("display","block");

}

