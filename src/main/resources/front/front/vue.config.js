module.exports = {
  lintOnSave: false,
  // node20 下 thread-loader 多线程与 vue-loader 兼容性差，关闭并行构建
  parallel: false,
  publicPath: process.env.NODE_ENV === 'development' ? './' : '././',
  outputDir: 'dist'
}