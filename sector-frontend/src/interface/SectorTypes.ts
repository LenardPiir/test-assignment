export interface Sector {
    code: string,
    name: string,
    children: Sector[]
}

export interface SectorForm {
    name: string,
    sectors: string[],
    agreeToTerms: boolean;
}