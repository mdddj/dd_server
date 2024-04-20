import { Project } from '@/types/project';

export interface Version {
  id?: number;
  title?: string;
  description?: string;
  downloadUrl?: string;
  platform?: VersionPlatform;
  createDate?: Date;
  htmlViewPage?: string;
  packageSize?: number;
  enable?: boolean;
  versionNumber?: string;
  projectName?: string;
  project?: Project;
  projectId?: number;
  isDirectLink?: boolean;
}

enum VersionPlatform {
  Android,
  Ios,
  Macos,
  Windows,
  Linux,
}
