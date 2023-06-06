/**
 * @license Copyright (c) 2003-2022, CKSource Holding sp. z o.o. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E'
	filebrowserUploadMethod = 'form';
	config.language='ko';						//언어설정
    config.enterMode =CKEDITOR.ENTER_BR;		//엔터키 입력시 br 태그 변경
    config.shiftEnterMode = CKEDITOR.ENTER_P;	//엔터키 입력시 p 태그로 변경
    config.startupFocus = true;					//시작시 포커스 설정
    config.docType = "<!DOCTYPE html>";			//문서타입 설정S
    config.htmlEncodeOutput = true;
};
