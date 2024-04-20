export interface BlogCategory {
  createTime: string;
  id: number;
  intro: string;
  logo: string;
  name: string;
}

export interface Blog {
  aliasString: string;
  author: string;
  category: BlogCategory;
  content: string;
  createTime: string;
  dateString: string;
  id: number | undefined;
  tags: BlogTag[];
  thumbnail: string;
  title: string;
  html: string;
}

export interface BlogTag {
  id: number;
  name: string;
}
