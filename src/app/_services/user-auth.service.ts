import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }
  public setRoles(roles: []): void {
    localStorage.setItem("roles", JSON.stringify(roles));
  }
  
  public getRoles(): [] {
    const rolesString = localStorage.getItem('roles');
    if (rolesString) {
      return JSON.parse(rolesString);
    } else {
      return [];
    }
  }
  public setToken(jwtToken: string) {
    localStorage.setItem("jwtToken", jwtToken);
  }
  
  public getToken(): string {
    return localStorage.getItem('jwtToken') || '';
  }
  public clear(){
    localStorage.clear();
  } 
  public isLoggedIn(){
    return this.getRoles() && this.getToken();
  }
}
