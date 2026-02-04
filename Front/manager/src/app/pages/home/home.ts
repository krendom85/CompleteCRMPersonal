import { Component } from '@angular/core';
import { ResponsiveTable } from '../../shared/components/responsive-table/responsive-table';
import { Action, AssignedUser, AvatarText, Badge, BadgeType, ColumnConfig, ColumnType, Currency, TableRow } from '../../shared/components/responsive-table/reponsive-table.models';



export interface PerfilData {
  client: AvatarText
  status: Badge;
  value: Currency;
  assigned: Array<AssignedUser>;
  actions: Action[];
}

@Component({
  selector: 'app-home',
  imports: [ResponsiveTable],
  templateUrl: './home.html',
  styleUrl: './home.css',
})

export class Home {
  columns: ColumnConfig[] = [
    { key: 'client', label: 'Cliente', type: ColumnType.AVATAR_TEXT },
    { key: 'status', label: 'Estado', type: ColumnType.BADGE },
    { key: 'value', label: 'Valor', type: ColumnType.CURRENCY },
    { key: 'assigned', label: 'Asignado', type: ColumnType.AVATARS },
    { key: 'actions', label: 'Acciones', type: ColumnType.ACTIONS }
  ];

  data: PerfilData[] = [
    {
      client: { name: 'Alex Rivera', subtext: 'alex@startup.com', img: 'https://i.pravatar.cc/150?u=1' },
      status: { label: 'Active', type: BadgeType.SUCCESS},
      value: { amount: 12450, currencySymbol: '$' },
      assigned: [
        { img: 'https://i.pravatar.cc/150?u=2', name: 'Sarah' },
        { img: 'https://i.pravatar.cc/150?u=3', name: 'Marcus' }
      ],
      actions: [{ icon: 'more_vert', tooltip: 'Acciones', callback: () => {} }]
    },
    {
      client: { name: 'Marta Soto', subtext: 'marta.s@corp.com', img: 'https://i.pravatar.cc/150?u=4' },
      status: { label: 'Pending', type: BadgeType.WARNING },
      value: { amount: 8300.50, currencySymbol: '$' },
      assigned: [
        { img: 'https://i.pravatar.cc/150?u=5', name: 'Janice' }
      ],
      actions: [{ icon: 'more_vert', tooltip: 'Acciones', callback: () => {} }]
    },
    {
      client: { name: 'Marcus Wright', subtext: 'm.wright@bangpulse.net', img: 'https://i.pravatar.cc/150?u=6' },
      status: { label: 'Overdue', type: BadgeType.DANGER },
      value: { amount: 8900.00, currencySymbol: '$' },
      assigned: [
        { img: 'https://i.pravatar.cc/150?u=7', name: 'Kevin' },
        { img: 'https://i.pravatar.cc/150?u=8', name: 'Sarah' }
      ],
      actions: [{ icon: 'more_vert', tooltip: 'Acciones', callback: () => {} }]
    },
    {
      client: { name: 'Janice Miller', subtext: 'janice@clientmedia.com', img: 'https://i.pravatar.cc/150?u=9' },
      status: { label: 'Active', type: BadgeType.SUCCESS },
      value: { amount: 21000.00, currencySymbol: '$' },
      assigned: [
        { img: 'https://i.pravatar.cc/150?u=10', name: 'Marta' }
      ],
      actions: [{ icon: 'more_vert', tooltip: 'Acciones', callback: () => {} }]
    },
    {
      client: { name: 'Kevin Park', subtext: 'kevin.park@nexus.dev', img: 'https://i.pravatar.cc/150?u=11' },
      status: { label: 'Pending', type: BadgeType.WARNING },
      value: { amount: 1500.00, currencySymbol: '$' },
      assigned: [
        { img: 'https://i.pravatar.cc/150?u=12', name: 'Janice' }
      ],
      actions: [{ icon: 'more_vert', tooltip: 'Acciones', callback: () => {} }]
    }
  ];
}
