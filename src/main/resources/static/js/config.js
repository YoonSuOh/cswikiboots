CKEDITOR.editorConfig = function(config) {
    config.allowedContent = true;
    config.filebrowserUploadMethod = 'form';
    config.height = 500;
    config.extraPlugins = 'lineutils';
    config.toolbarGroups = [
        { name: 'basicstyles', groups: [ 'basicstyles' ] },
        { name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align' ] },
        { name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
        { name: 'tools', groups: [ 'tools' ] },
        { name: 'document', groups: [ 'mode' ] },
        { name: 'others', groups: [ 'others' ] },
        { name: 'about', groups: [ 'about' ] }
    ];
    config.removeButtons = 'Cut,Copy,Paste,Undo,Redo,Anchor,Underline,Strike,Subscript,Superscript';
};