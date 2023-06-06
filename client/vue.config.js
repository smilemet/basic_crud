const { defineConfig } = require("@vue/cli-service");
const Dotenv = require("dotenv-webpack");

module.exports = defineConfig({
  devServer: { port: 8081 },
  configureWebpack: {
    plugins: [new Dotenv()],
  },
  transpileDependencies: true,
});
