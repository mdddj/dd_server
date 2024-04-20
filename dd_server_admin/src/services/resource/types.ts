import { FileInfo } from '@/services/file/type';
import { User } from '@/types/user';

export interface ResourcesCategory {
  id?: number;
  name?: string;
  logo: string;
  description: string;
  // announcement?: MyResources,
  users: User[];
  type?: string;
  navJsonString?: string;
  childers: ResourcesCategory[];
  parentNode?: ResourcesCategory;
  level?: number;
  files: FileInfo[];
  // resources: MutableSet<MyResources>,
}
