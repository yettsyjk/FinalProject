export class Snitch {
  id: number;
  title: string;
  description: string;
  createDate: string;
  imgUrl: string;
  enabled: boolean;
  resolved: boolean;
  resolutionDate: string;
  resolution: string;


  constructor(
    id?: number,
    title?: string,
    description?: string,
    createDate?: string,
    imgUrl?: string,
    enabled?: boolean,
    resolved?: boolean,
    resolutionDate?: string,
    resolution?: string) {

      this.id = id;
      this.title = title;
      this.description = description;
      this.createDate = createDate;
      this.imgUrl = imgUrl;
      this.enabled = enabled;
      this.resolved = resolved;
      this.resolutionDate = resolutionDate;
      this.resolution = resolution;
  }
}
