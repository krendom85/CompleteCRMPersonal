export enum ColumnType {
  AVATAR_TEXT = 'avatar_text',
  BADGE = 'badge',
  TEXT = 'text',
  NUMBER = 'number',
  DATE = 'date',
  BOOLEAN = 'boolean',
  CURRENCY = 'currency',
  AVATARS = 'avatars',
  ACTIONS = 'actions'
}

export enum BadgeType {
  SUCCESS = 'success',
  WARNING = 'warning',
  DANGER = 'danger'
}

export enum StatusType {
  ACTIVE = 'active',
  PENDING = 'pending',
  INACTIVE = 'inactive',
  DELETE = 'delete'
}

export interface ColumnConfig{
  key: string;
  label: string;
  type: ColumnType;
}

export interface TableRow {
  avatarText?: AvatarText;
  badge?: Badge;
  dateInfo?: DateInfo;
  booleanInfo?: BooleanInfo;
  numberInfo?: NumberInfo;
  textInfo?: TextInfo;
  statusTypeInfo?: StatusTypeInfo;
  currency?: Currency;
  assignedUsers?: AssignedUser[];
  action?: Action;
  [key: string]: unknown; // Para permitir cualquier otra propiedad dinámica
}

export interface AvatarText {
  name: string;
  subtext: string;
  img: string;
}

export interface Badge {
  label: string;
  type: BadgeType;
}

export interface DateInfo {
  date: Date;
  format?: string;
}

export interface BooleanInfo {
  value: boolean;
  trueLabel?: string;
  falseLabel?: string;
}

export interface NumberInfo {
  value: number;
  format?: Intl.NumberFormatOptions;
}

export interface TextInfo {
  value: string;
  truncate?: boolean;
}

export interface StatusTypeInfo {
  value: StatusType;
}

export interface Currency {
  amount: number;
  currencySymbol: string;
}

export interface AssignedUser {
  img: string;
  name: string;
}

export interface Action {
  icon: string;
  tooltip: string;
  callback: () => void;
}

