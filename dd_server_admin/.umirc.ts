import { defineConfig } from '@umijs/max';

export default defineConfig({
  antd: {
    // theme: {
    //   token: {
    //     colorPrimary: "#d21279",
    //     colorInfo: "#d21279",
    //     colorSuccess: "#4fb51c",
    //     colorWarning: "#f7a500",
    //   },
    // },
  },
  access: {},
  model: {},
  initialState: {},
  request: {},
  layout: {
    title: '典典的小卖部',
  },
  routes: [
    {
      path: '/',
      redirect: '/home',
      access: 'canSeeAdmin',
    },
    {
      name: '首页',
      path: '/home',
      component: './Home',
      icon: 'HomeOutlined',
    },
    {
      name: '登录',
      path: '/login',
      component: './Login',
      layout: false,
    },
    {
      name: '初始化管理员账号',
      path: '/init',
      component: './Login/CreateAdminPage',
      layout: false,
    },
    {
      name: '用户管理',
      path: '/users',
      component: './Users',
      icon: 'UserOutlined',
    },
    {
      name: '企业管理',
      path: '/enterprise',
      icon: 'ClusterOutlined',
      routes: [
        {
          name: '企业列表',
          path: '/enterprise/list',
          component: './Enterprise/list',
        },
        {
          name: '创建企业',
          path: '/enterprise/create',
          component: './Enterprise/create',
        },
      ],
    },
    {
      name: '权限管理',
      path: '/role',
      icon: 'SafetyOutlined',
      routes: [
        {
          name: '权限列表',
          path: '/role/list',
          component: './Role/list',
        },
      ],
    },
    {
      name: '文件管理',
      path: '/file',
      icon: 'FileOutlined',
      routes: [
        {
          name: '上传文件',
          path: '/file/upload',
          component: './File/upload',
        },
        {
          name: '文件列表',
          path: '/file/list',
          component: './File/list',
        },
      ],
    },
    {
      name: '文章管理',
      path: '/blog',
      icon: 'CloudOutlined',
      routes: [
        {
          name: '文章列表',
          path: '/blog/list',
          component: './Blog/list',
        },
        {
          name: '发布文章',
          path: '/blog/add',
          component: './Blog/add',
        },
        {
          name: '博客分类',
          path: '/blog/categorys',
          component: './Blog/Category',
        },
      ],
    },
    {
      name: '资源管理',
      path: '/resource',
      icon: 'ProfileOutlined',
      routes: [
        {
          name: '分类列表',
          path: '/resource/category',
          component: './Resource/CategoryList',
        },
        {
          name: '动态列表',
          path: '/resource/list',
          component: './Resource/MyResourceList',
        },
        {
          name: '添加动态',
          path: '/resource/add',
          component: './Resource/AddResourceForm',
        },
      ],
    },
    {
      name: '订单管理',
      path: '/order',
      icon: 'ShoppingOutlined',
      routes: [
        {
          name: '订单列表',
          path: '/order/dataoke',
          component: './Order/DataokeOrder',
        },
        {
          name: '折淘客订单查询',
          path: '/order/zhetaoke',
          component: './Order/ZheTaokeOrder',
        },
        {
          name: '大淘客订单工具',
          path: '/order/dtkutil',
          component: './Order/DtkOrderUtil',
        },
      ],
    },
    {
      name: '大淘客管理',
      path: '/dtk',
      icon: 'IdcardOutlined',
      routes: [
        {
          name: '账号列表',
          path: '/dtk/accounts',
          component: './Dtk/Account',
        },
      ],
    },
    {
      name: '版本管理',
      path: '/version',
      icon: 'ForkOutlined',
      routes: [
        {
          name: '版本列表',
          path: '/version/list',
          component: './Version/VersionList',
        },
        {
          name: '发布新版本',
          path: '/version/add',
          component: './Version/AddNewVersion',
        },
      ],
    },
    {
      name: '友链',
      path: '/friend',
      icon: 'ProjectOutlined',
      routes: [
        {
          name:"列表",
          path:"/friend/list",
          component: './Friend/index'
        }
      ]
    },
    {
      name: '字典',
      path: '/text',
      icon: 'ProjectOutlined',
      routes: [
        {
          name:"列表",
          path:"/text/list",
          component: './Text/List'
        }
      ]
    },
    {
      name: '项目管理',
      path: '/project',
      icon: 'ProjectOutlined',
      routes: [
        {
          name: '新增项目',
          path: '/project/new',
          component: './Project/AddNewProject',
        },
        {
          name: '项目列表',
          path: '/project/list',
          component: './Project/List',
        },
      ],
    },
  ],

  npmClient: 'pnpm',
  tailwindcss: {},
  esbuildMinifyIIFE: true
});
