export interface DTKDetail {
  id?: number;
  appKey: string;
  appSecret: string;
  title?: string;
  intro?: string;
  selectDefault?: boolean;
  logo?: string;
  relationId?: string;
}

/**
 * 设置默认账号的参数
 */
export interface SetDefaultDtkAccountParam {
  /**
   * true: 启用, false: 取消启用
   */
  action: boolean;
  /**
   * ID
   */
  id: number;
}

/**
 * 验证大淘客账号是否可用参数
 */
export interface DtkVerifyParam {
  /**
   * 大淘客appKey
   */
  appKey: string;
  /**
   * 大淘客appSecret
   */
  appSecret: string;
}
