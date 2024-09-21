
let isProduct =  process.env.NODE_ENV === 'production';
let host = isProduct ? 'https://api.itbug.shop' : 'http://localhost:8082';
let wss = isProduct ? 'wss://api.itbug.shop' : 'ws://localhost:8082';
export const HOST_NAME = host
export const WS_URL = wss
export const DEFAULT_NAME = '典后台'
