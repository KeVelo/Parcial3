
## Video Manager App

### Descripción
Esta aplicación de Android permite grabar, ver y reproducir videos directamente en el dispositivo. Fue diseñada con una interfaz limpia y atractiva usando Jetpack Compose y Material3,
además de una estructura de navegación sencilla entre las diferentes funcionalidades.

### Funcionalidades Implementadas

1. **Pantalla Principal con Opciones**:
   - **Grabar Video**: Al hacer clic en el botón "Grabar Video", se solicita el permiso de cámara (si no ha sido otorgado previamente) y luego se abre la cámara para grabar un video.
El video se guarda en una carpeta específica en el almacenamiento interno de la aplicación.
   - **Ver Videos Guardados**: Muestra una lista de videos grabados previamente, donde cada video tiene la opción de ser reproducido y eliminado.
   - **Reproducir Último Video**: Reproduce automáticamente el último video grabado.

2. **Lista de Videos Guardados**:
   - Se muestra una lista de todos los videos grabados con la aplicación.
   - Cada video tiene dos botones:
     - **Reproducir**: Abre el video seleccionado en una nueva pantalla para verlo.
     - **Eliminar**: Permite borrar el video específico del almacenamiento interno.

3. **Navegación y Regreso a la Pantalla Principal**:
   - En la pantalla de lista de videos y en la pantalla de reproducción, hay un botón de "Volver a la pantalla principal" para regresar a la pantalla inicial.

4. **Estilos y Temas**:
   - Se aplicó un tema personalizado usando Material3 con colores específicos (como azul oscuro y gris claro) para darle un aspecto profesional y atractivo a la interfaz.
   - Botones y textos utilizan colores coherentes para una mejor experiencia de usuario.

### Requisitos

1. **Permisos**:
   - **Cámara**: La aplicación solicita permiso para acceder a la cámara para poder grabar videos.
   - **Almacenamiento**: Aunque no se solicitan permisos de almacenamiento adicionales, es importante que el dispositivo tenga espacio disponible en el almacenamiento interno.

### Cómo Ejecutar el Proyecto

1. Clona el repositorio y ábrelo en Android Studio.
2. Ejecuta el proyecto en un dispositivo o emulador que tenga Android 6.0 o superior.
3. Asegúrate de conceder los permisos necesarios cuando se soliciten.
