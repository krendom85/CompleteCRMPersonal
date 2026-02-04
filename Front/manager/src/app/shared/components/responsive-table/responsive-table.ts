import { Component, Input } from '@angular/core';
import { ColumnConfig, TableRow } from './reponsive-table.models';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-responsive-table',
  imports: [CommonModule],
  templateUrl: './responsive-table.html',
  styleUrl: './responsive-table.css',
})
export class ResponsiveTable {
  @Input() columns: ColumnConfig[] = [];
  @Input() data: any[] = [];

  asAvatarText(row: any, key: string) {
    return row[key] as import('./reponsive-table.models').AvatarText;
  }
  asBadge(row: any, key: string) {
    return row[key] as import('./reponsive-table.models').Badge;
  }
  asCurrency(row: any, key: string) {
    return row[key] as import('./reponsive-table.models').Currency;
  }
  asAssignedUsers(row: any, key: string) {
    return row[key] as import('./reponsive-table.models').AssignedUser[];
  }
  asActions(row: any, key: string) {
    return row[key] as import('./reponsive-table.models').Action[];
  }
  asDateInfo(row: any, key: string) {
    return row[key] as import('./reponsive-table.models').DateInfo;
  }
  asBooleanInfo(row: any, key: string) {
    return row[key] as import('./reponsive-table.models').BooleanInfo;
  }
  asNumberInfo(row: any, key: string) {
    return row[key] as import('./reponsive-table.models').NumberInfo;
  }
  asTextInfo(row: any, key: string) {
    return row[key] as import('./reponsive-table.models').TextInfo;
  }
  asStatusTypeInfo(row: any, key: string) {
    return row[key] as import('./reponsive-table.models').StatusTypeInfo;
  }
}
