import { Enterprise } from '@/types/enterprose';

export interface User {
  accountNonExpired: boolean;
  accountNonLocked: boolean;
  authorities: any[];
  credentialsNonExpired: boolean;
  email: string;
  enabled: boolean;
  id: number;
  loginNumber: string;
  loginTime: string;
  nickName: string;
  openAiFlag: boolean;
  openAiTokens: number | undefined;
  phone: string;
  picture: string;
  resourcesCategories: any[];
  roles: any[];
  status: number;
  type: number;
  username: string;
  vip: Vip;
  wallet: null;
  enterprise: Enterprise | undefined;
}

export enum Vip {
  no,
  vip,
  super,
}
