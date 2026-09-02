const base = {
    get() {
        return {
            url : "http://localhost:8080/springbootsnu6t/",
            name: "springbootsnu6t",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springbootsnu6t/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于Java Web的新能源汽车信息咨询服务"
        } 
    }
}
export default base
