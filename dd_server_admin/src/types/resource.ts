import { FileInfo } from '@/services/file/type';
import { ResourcesCategory } from '@/services/resource/types';
import { User } from './user';

export interface MyResources {
  id?: number;
  user?: User;
  category?: ResourcesCategory;
  title?: string;
  content?: string;
  createDate?: Date;
  updateDate?: Date;
  label?: string;
  thumbnailImage?: string;
  description?: string;
  links?: string;
  type?: string;
  authority?: number;
  clickCount?: number;
  fileInfo?: FileInfo;
  images?: FileInfo[];
  browserUrl?: string;
}
