import { ToastType } from '@/types/result';

export interface ResponseStructure {
  state: number;
  data: any;
  message: string;
  success: boolean;
  type: ToastType;
}

export class ApiError extends Error {
  info: ResponseStructure;

  constructor(res: ResponseStructure) {
    super(res.message);
    super.name = 'BizError';
    this.info = res;
  }
}