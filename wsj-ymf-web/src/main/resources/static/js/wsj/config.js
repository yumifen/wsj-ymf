var debug = true;
requirejs.config({
	baseUrl: '/', //js主文件夹路径
	paths: {
        'base': '/js/wsj/core/base',
        'utils': '/js/wsj/core/utils',
    },
	urlArgs: "bust=" + (debug ? 'final':(new Date()).getTime()),
});