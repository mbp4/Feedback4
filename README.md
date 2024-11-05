# Feedback4
 
link al repositorio: https://github.com/mbp4/Feedback4.git

## Proyecto

En esta continuación del Feedback se nos pedía realizar: 

 -> Toda la configuración con las bases de Firebase, es decir, reutilizamos los códigos anteriores. 

 -> Widget para mostrar las novelas favoritas de los usuarios. 

 -> Traspasar el listado de novelas y sus detalles a un fragmento.


## EXPLICACIÓN

### Login

```
Clase LoginActivity extiende AppCompatActivity:
    Atributo db de tipo FirebaseFirestore inicializado a firestore
    Objeto companion:
        Atributo modoOscuro de tipo Boolean inicializado a false
        Atributo mail de tipo String inicializado vacío

    Método onCreate(Bundle?):
        Llamar a la función super.onCreate
        Asignar layout activity_login como contenido de la vista
        Declarar variable mail para obtener el campo de texto de correo (editMail)
        Declarar variable password para obtener el campo de texto de contraseña (editPassword)
        Configurar el inputType del campo password para ocultar el texto
        Declarar variable registro para obtener el botón de registro (btnRegistro)
        Declarar variable loginButton para obtener el botón de iniciar sesión (btnIniciar)
        
        Acción del botón loginButton al hacer clic:
            Llamar a la función hacerLogin con los valores de mail y password

        Acción del botón registro al hacer clic:
            Crear un Intent para ir a la actividad RegistroActivity
            Iniciar la actividad con el Intent

        Comprobar el estado de la sesión:
            Si la sesión está abierta:
                Mostrar mensaje "Sesión cerrada"
                Cambiar SplashActivity.sesion a false
            Si la sesión está cerrada:
                Mostrar mensaje "Sesión cerrada"

    Método hacerLogin(mail, password):
        Si el mail y el password no están vacíos:
            Buscar en la base de datos de usuarios en la colección "dbUsuarios" por el mail y el password
            Si la consulta tiene éxito:
                Si no se encuentran documentos:
                    Mostrar mensaje "Usuario no encontrado"
                Si se encuentran documentos:
                    Asignar el valor del campo "modo" del primer documento a LoginActivity.modoOscuro
                    Si modoOscuro es de tipo Boolean, actualizar LoginActivity.modoOscuro
                    Si no, asignar false a LoginActivity.modoOscuro por defecto
                    Llamar a la función activarModo con LoginActivity.modoOscuro
                    Asignar el mail a LoginActivity.mail
                    Crear un Intent para ir a la actividad MainActivity
                    Iniciar la actividad con el Intent
                    Mostrar mensaje "Sesión iniciada"
            Si la consulta falla:
                Mostrar mensaje "Error al iniciar sesión"
        Si mail o password están vacíos:
            Mostrar mensaje "Por favor, ingrese un correo electrónico y una contraseña"

    Método activarModo(modoOscuro):
        Si modoOscuro es true:
            Activar modo oscuro en la aplicación
        Si no:
            Activar modo claro en la aplicación
        Aplicar los cambios visuales
```

Esta clase esta formada por una imagen que simule al usuario, campos correspondientes para iniciar sesión (campo para introducir el correo electrónico y la contraseña) y dos botones: 

 -> Botón de inicio de sesión: comprueba que los datos introducidos por el usuario sean validos, en el cualquiera de los casos se muestra un Toast con un mensaje u otro.

 -> Botón de registro: redirige al usuario a la pantalla de registro.

### Registro

```
Clase RegistroActivity extiende AppCompatActivity:
    Atributo btnAlta2 de tipo Button (sin inicializar al principio)
    Atributo btnCancelar2 de tipo Button (sin inicializar al principio)
    Atributo editmail2 de tipo EditText (sin inicializar al principio)
    Atributo editpassword2 de tipo EditText (sin inicializar al principio)
    Atributo db de tipo FirebaseFirestore inicializado a firestore

    Método onCreate(Bundle?):
        Llamar a la función super.onCreate
        Asignar layout activity_registro como contenido de la vista
        Inicializar btnAlta2 con el botón de alta (btnAlta)
        Inicializar btnCancelar2 con el botón de cancelar (btnCancelar2)
        Inicializar editmail2 con el campo de correo electrónico (editMail2)
        Inicializar editpassword2 con el campo de contraseña (editPassword2)
        Configurar el inputType de editpassword2 para ocultar el texto

        Acción del botón btnAlta2 al hacer clic:
            Llamar a la función registro
            Crear un Intent para ir a la actividad MainActivity
            Iniciar la actividad con el Intent

        Acción del botón btnCancelar2 al hacer clic:
            Finalizar la actividad actual

    Método registro():
        Obtener el texto de editmail2 como mail
        Obtener el texto de editpassword2 como password

        Si mail y password no están vacíos:
          Buscar en la colección "dbUsuarios" por el correo mail
            Si la consulta tiene éxito:
                Si no se encuentran documentos:
                    Crear un objeto nuevoUsuario con mail, password y modoOscuro = false
                    Agregar nuevoUsuario a la colección "dbUsuarios"
                    Si la operación tiene éxito:
                        Mostrar mensaje "El usuario se ha registrado correctamente"
                        Asignar el mail a LoginActivity.mail
                        Crear un Intent para ir a la actividad MainActivity
                        Iniciar la actividad con el Intent
                        Finalizar la actividad actual
                    Si la operación falla:
                        Mostrar mensaje "Error al registrar el usuario"
                Si se encuentran documentos:
                    Mostrar mensaje "El mail ya está registrado"
            Si la consulta falla:
                Mostrar mensaje "Error al comprobar el usuario"
        Si mail o password están vacíos:
            Mostrar mensaje "Por favor ingresa un correo y contraseña válidos"

```

Esta pantalla permite al usuario crear uno nuevo para poder navegar y esta compuesta por los campos a rellenar (mail y contraseña) y dos botones: 

 -> Botón registrar: le pide al usuario unos datos, de los cuáles comprobará si el correo ya está asignado a un usuario, en el caso de que lo este no podrá avanzar a la siguiente pantalla, y si el registro ha sido exitoso se le indica y se le permite continuar.

 -> Botón cancelar: devuelve al usuario a la página inicial.

### Usuario

Con esta clase creamos un objeto usuario para poder trabajar en las diferentes actividades: 

```
Clase de datos Usuario:
    Atributos:
        mail de tipo String
        password de tipo String
        modo de tipo Boolean

    Constructor secundario (sin parámetros):
        Llama al constructor principal asignando valores por defecto:
            mail = cadena vacía
            password = cadena vacía
            modo = false
```


 ### Configuración

 En esta clase se le permite al usuario administrar el usuario con el que se ha loggeado, su pseudocódigo sería: 

 ```
 Clase AjustesActivity extiende AppCompatActivity:
    Atributo btnMain de tipo Button (sin inicializar al principio)
    Atributo btnCerrar de tipo Button (sin inicializar al principio)
    Atributo textoInfo de tipo TextView (sin inicializar al principio)

    Método onCreate(Bundle?):
        Llamar a la función super.onCreate
        Asignar layout activity_configuracion como contenido de la vista
        Inicializar btnMain con el botón (btnMain)
        Inicializar btnCerrar con el botón (btnCerrarSesion)
        Inicializar textoInfo con el TextView (textMailInfo)
        Establecer SplashActivity.sesion como false (cerrar sesión actual)
        
        Obtener el correo electrónico guardado en LoginActivity.mail
        Asignar el correo electrónico al texto de textoInfo

        Acción del botón btnMain al hacer clic:
            Crear un Intent para ir a la actividad MainActivity
            Iniciar la actividad con el Intent

        Acción del botón btnCerrar al hacer clic:
            Crear un cuadro de diálogo de alerta:
                Título: "Cerrar Sesión"
                Mensaje: "¿Estás seguro de que quieres cerrar sesión?"
                Botón positivo ("Sí"):
                    Crear un Intent para ir a la actividad SplashActivity
                    Iniciar la actividad con el Intent
                Botón negativo ("No"):
                    No hacer nada
            Mostrar el diálogo de alerta
 ```
 Esta clase contiene una imagen para simular el usuario, una espacio donde se muestra el correo con el que se ha iniciado sesión y dos botones: 

  -> Botón cerrar sesión: este botón cierra la sesión después de que el usuario le confirme.

  -> Botón cancelar: este botón devuelve al usuario a la pantalla anterior.


 ### Novela

 Con esta clase creamos un objeto novela para podeer trabajar con varios en las diferentes actividades: 

 ```
Clase de datos Novela con los siguientes atributos:
    - titulo como String
    - autor como String
    - año como Int
    - sinopsis como String
    - fav como boolean 

    Constructor():
        Llamar al constructor principal con valores por defecto:
            titulo vacío, autor vacío, año igual a 0, sinopsis vacía, fav falso
```

### Base de datos de Novelas

Para esto haremos uso de Firebase, con esto podremos hacer uso de una base de datos donde se modificarán las novelas, es decir, se añadiran o se eliminarán de esta.

Visualmente uno de los elementos se vería así: 

<img width="1163" alt="Captura de pantalla 2024-10-29 a las 12 24 30" src="https://github.com/user-attachments/assets/1aebd0ed-c28c-4233-b789-45144ceb699f">


### Adaptador para las Novelas

Con esta activity buscamos mostrar la lista que se ha creado de novelas, esta activity es necesaria ya que es la que hace posible que se muestre el recyclerView (el utilizado para poder crear la lista de novelas): 

```
Clase NovelasAdapter (recibe una lista mutable de novelas y una función para manejar clics)

    Clase interna NovelasViewHolder (hereda de RecyclerView.ViewHolder)
        Variables en el layout del item:
            TextView textTituloNovel 
            TextView textAutorNovel 
            Botón btnVer 
            Botón btnBorrar 
            Botón btnFavorito 
            Botón btnXFavorito 
            ImageView estrella 

    Método onCreateViewHolder (crea una nueva vista para cada item en la lista)
        Inflar la vista del layout item_novela
        Devolver un nuevo NovelasViewHolder con la vista inflada

    Método onBindViewHolder (vincula los datos con la vista en la posición actual)
        Obtener la novela actual de la lista en la posición indicada
        Mostrar el título y autor de la novela en los TextViews correspondientes
        Acción al hacer clic en btnVer:
            Llamar a la función onNovelasClick con la novela actual y la acción ACCION_VER
        Acción al hacer clic en btnBorrar:
            Mostrar un diálogo de confirmación para borrar la novela
            i el usuario confirma, llamar a onNovelasClick con la novela y la acción ACCION_BORRAR
        Acción al hacer clic en btnFavorito:
            Llamar a onNovelasClick con la novela actual y la acción ACCION_FAV
        Acción al hacer clic en btnXFavorito:
            Llamar a onNovelasClick con la novela actual y la acción ACCION_XFAV
        Si la novela es favorita (atributo fav es true):
            Cambiar la imagen de la estrella a rellena
        Si no es favorita:
            Cambiar la imagen de la estrella a vacía

    Método getItemCount (devuelve el número de novelas en la lista)
        Devolver el tamaño de la lista de novelas

```

Este adaptador se encarga de la gestión de la visualización de las novelas y los botones. 

Estos 2 botones se encargan de llevar al usuario a una pantalla encargada de mostrar toda la información de la novela elegida por el usuario (btnVer) y de eliminar la novela que el usuario decida (btnBorrar).

A parte tendremos los botones que se encargan de cambiar el atributo de la novela seleccionada para que esta se convierta en favorita o no mostrando una imagen de una estrella vacia en el caso de que la novela no sea favorita y de una estrella rellena en el caso de que sea una favorita. 

Por otra parte el adaptador se encarga también de mostrar el tamaño de la lista.

### Actividad para mostrar toda la informacion de la Novela

En esta activity se mostrará toda la información de la novela pulsada:

```
Clase VerNovelaFragment Extiende Fragmento

    Variables:
        txt1: TextView
        txt2: TextView
        txt3: TextView
        txt4: TextView
        btnVolver: Botón

    Método onCreateView(inflador: LayoutInflater, contenedor: ViewGroup, estadoGuardado: Bundle) Retorna View
        Retornar inflador.inflate(R.layout.fragment_ver, contenedor, falso)

    Método onViewCreated(vista: View, estadoGuardado: Bundle)
        Llamar a super.onViewCreated(vista, estadoGuardado)

        txt1 = vista.findViewById(R.id.txt1)
        txt2 = vista.findViewById(R.id.txt2)
        txt3 = vista.findViewById(R.id.txt3)
        txt4 = vista.findViewById(R.id.txt4)
        btnVolver = vista.findViewById(R.id.btnVolver)

        Si arguments no es nulo Entonces
            txt1.text = arguments.getString("Titulo")
            txt2.text = arguments.getString("Autor")
            txt3.text = arguments.getInt("Año").convertirAString()
            txt4.text = arguments.getString("Sinopsis")
        Fin Si

        btnVolver.setOnClickListener
            parentFragmentManager.popBackStack()
            (actividad como MainActivity)?.cambiar(ListadoNovelasFragmento())
        Fin btnVolver.setOnClickListener

Fin Clase

```
En este fragmento nos encontramos con varias variables que van asociadas a las ya definidas en los layouts, estas se encargarán de mostrar cada uno de los atributos de la novela que se ha pulsado.

Por otra parte el btnVolver también se encuentra asociado a uno ya definido en el layout, este se encarga de devolver al usuario al listado, cambiando el fragmento.


## Main Activity

Para realizar la aplicacion necesitamos una aplicacion inicial que nos servirá de pantalla de inicio, su pseudocódigo sería:

```

Clase MainActivity Extiende AppCompatActivity

    Variables:
        btnAlta: Botón
        btnAcercaDe: Botón
        btnTema: ToggleButton
        btnConfig: ImageButton
        novelasAdapter: NovelasAdapter
        listadoNovelasF: ListaMutable de Novela = nueva ListaMutable()
        db: FirebaseFirestore = Firebase.firestore

    Companión objeto:
        Constante ACCION_VER = 1
        Constante ACCION_BORRAR = 2
        Constante ACCION_FAV = 3
        Constante ACCION_XFAV = 4

    Método onCreate(estadoGuardado: Bundle)
        Llamar a super.onCreate(estadoGuardado)
        setContentView(R.layout.activity_main) // Establecer la vista de la actividad

        btnAlta = findViewById(R.id.btnAlta)
        btnAcercaDe = findViewById(R.id.btnAcercaDe)
        btnTema = findViewById(R.id.btnTema)
        btnConfig = findViewById(R.id.btnAjustes)

        // Asociar los botones con los identificadores del layout
        btnTema.setChecked(LoginActivity.modoOscuro)

        btnTema.setOnClickListener
            Si btnTema.isChecked() Entonces
                activarModo(true)
            Sino
                activarModo(false)
            Fin Si
        Fin btnTema.setOnClickListener

        btnAlta.setOnClickListener
            Crear nuevo Intent para NuevaNovelaActivity
            startActivity(intent)
        Fin btnAlta.setOnClickListener // Botón para alta de novelas

        btnAcercaDe.setOnClickListener
            Crear nuevo Intent para AcercaDeActivity
            startActivity(intent)
        Fin btnAcercaDe.setOnClickListener // Botón para Acerca de

        btnConfig.setOnClickListener
            Crear nuevo Intent para AjustesActivity
            startActivity(intent)
        Fin btnConfig.setOnClickListener

        Si estadoGuardado es nulo Entonces
            cambiar(ListadoNovelasFragmento())
        Fin Si

    Fin Método

    Método activarModo(modoOscuro: Boolean)
        Si modoOscuro Entonces
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        Sino
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        Fin Si
        delegate.applyDayNight()
        LoginActivity.modoOscuro = modoOscuro
        db.collection("dbUsuarios")
            .whereEqualTo("mail", LoginActivity.mail)
            .get()
            .addOnSuccessListener { documentos ->
                id = documentos.documents[0].id
                db.collection("dbUsuarios")
                    .document(id)
                    .update("modo", modoOscuro)
            }
    Fin Método

    Método cambiar(fragmento: Fragment)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmento, fragmento)
            .commit()
    Fin Método

Fin Clase

```
En esta actividad, se presenta un TextView inicial que muestra el título de la aplicación. A continuación, se encuentran dos botones:

 -> Botón "Añadir Novela": Este botón redirige al usuario a una nueva pantalla donde podrá ingresar todos los datos de una novela y agregarla a la base de datos de novelas existente.
 
 ->Botón "Acerca de": Este botón lleva al usuario a una pantalla donde se muestra información sobre el autor de la aplicación.

## Listado en Fragmento

En este fragmento se muestra el listado de las novelas: 

```
Clase ListadoNovelasFragmento Extiende Fragmento

    Variables:
        recyclerNovelas: RecyclerView
        novelasAdapter: NovelasAdapter
        listadoNovelasF: ListaMutable de Novela = nueva ListaMutable()
        db: FirebaseFirestore = Firebase.firestore

    Método onCreateView(inflador: LayoutInflater, contenedor: ViewGroup, estadoGuardado: Bundle) Retorna View
        Retornar inflador.inflate(R.layout.fragment_listado, contenedor, falso)

    Método onViewCreated(vista: View, estadoGuardado: Bundle)
        Llamar a super.onViewCreated(vista, estadoGuardado)

        recyclerNovelas = vista.findViewById(R.id.recyclerNovelas)
        recyclerNovelas.layoutManager = LinearLayoutManager(context)

        cargarNovelas()
    Fin Método

    Método cargarNovelas()
        db.collection("dbNovelas")
            .get()
            .addOnSuccessListener { documentos ->
                listadoNovelasF.clear()
                Para cada documento en documentos Hacer
                    novela = documento.toObject(Novela::class.java)
                    listadoNovelasF.add(novela)
                Fin Para
                prepararRecyclerView()
            }
            .addOnFailureListener { excepcion ->
                Log.w(TAG, "Error al obtener las novelas de la base de datos", excepcion)
            }
    Fin Método

    Método verNovela(novela: Novela)
        fragmento = VerNovelaFragment().apply {
            arguments = Bundle().apply {
                putString("Titulo", novela.titulo)
                putString("Autor", novela.autor)
                putInt("Año", novela.año)
                putString("Sinopsis", novela.sinopsis)
            }
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmento, fragmento)
            .addToBackStack(null)
            .commit()
    Fin Método

    Método prepararRecyclerView()
        novelasAdapter = NovelasAdapter(listadoNovelasF) { novela, accion ->
            Si accion == ACCION_VER Entonces
                verNovela(novela)
            Sino Si accion == ACCION_BORRAR Entonces
                borrarNovela(novela)
            Sino Si accion == ACCION_FAV Entonces
                añadirFavorita(novela)
            Sino Si accion == ACCION_XFAV Entonces
                xFav(novela)
            Fin Si
        }
        recyclerNovelas.adapter = novelasAdapter
        novelasAdapter.notifyDataSetChanged()
    Fin Método

    Método xFav(novela: Novela)
        db.collection("dbNovelas")
            .whereEqualTo("titulo", novela.titulo)
            .get()
            .addOnSuccessListener { documentos ->
                Para cada documento en documentos Hacer
                    documento.reference.update("fav", falso)
                Fin Para
                mostrarNovelas()
            }
    Fin Método

    Método borrarNovela(novela: Novela)
        db.collection("dbNovelas")
            .whereEqualTo("titulo", novela.titulo)
            .get()
            .addOnSuccessListener { documentos ->
                Para cada documento en documentos Hacer
                    documento.reference.delete()
                Fin Para
                mostrarNovelas()
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error al borrar la novela de la base de datos", e)
            }
    Fin Método

    Método añadirFavorita(novela: Novela)
        db.collection("dbNovelas")
            .whereEqualTo("titulo", novela.titulo)
            .get()
            .addOnSuccessListener { documentos ->
                Para cada documento en documentos Hacer
                    documento.reference.update("fav", verdadero)
                Fin Para
                mostrarNovelas()
            }
    Fin Método

    Método mostrarNovelas()
        db.collection("dbNovelas")
            .get()
            .addOnSuccessListener { documentos ->
                listadoNovelasF.clear()
                Para cada documento en documentos Hacer
                    novela = documento.toObject(Novela::class.java)
                    listadoNovelasF.add(novela)
                Fin Para
                prepararRecyclerView()
            }
            .addOnFailureListener { excepcion ->
                Log.w(TAG, "Error al obtener las novelas de la base de datos", excepcion)
            }
    Fin Método

    Método onDetach()
        Llamar a super.onDetach()
        (actividad como MainActivity)?.cambiar(this)
    Fin Método

    Método onResume()
        Llamar a super.onResume()
        mostrarNovelas()
    Fin Método

Fin Clase

```
En este fragmento se muestra la lista de novelas existentes, que incluye el título y el autor de cada obra. Para cada novela en la lista, hay 4 botones:

 -> Botón "Ver": Este botón redirige al usuario a un fragmento que presenta toda la información de la novela seleccionada.
 
 -> Botón "Eliminar": Este botón elimina la novela seleccionada de la base de datos.

 -> Botones favoritos: tenemos dos botones, uno de ello marcará la novela como favorita y el otro la quitará de favoritos.

 -> Imagen: para indicar al usuario que la novela ya es favorita se muestra una imagen que cambia dependiendo del atributo de la novela que indica si es o no favorita.


## Widget

Creamos un widget en el que se muestren las novelas favoritas de los usuarios: 

```
Clase FavoritasWidget Extiende AppWidgetProvider

    Variables:
        db: FirebaseFirestore = Firebase.firestore

    Método onUpdate(contexto: Context, appWidgetManager: AppWidgetManager, appWidgetIds: Lista de enteros)
        Para cada appWidgetId en appWidgetIds Hacer
            actualizarWidget(contexto, appWidgetManager, appWidgetId)
        Fin Para
    Fin Método

    Método actualizarWidget(contexto: Context, appWidgetManager: AppWidgetManager, appWidgetId: Entero)
        views = RemoteViews(contexto.packageName, R.layout.widget_novelas_favoritas)

        views.removeAllViews(R.id.widgetFavo)

        db.collection("dbNovelas")
            .whereEqualTo("fav", verdadero)
            .get()
            .addOnSuccessListener { documentos ->
                Para cada documento en documentos Hacer
                    titulo = documento.getString("titulo") ?: "Sin título"
                    autor = documento.getString("autor") ?: "Sin autor"

                    novelaView = RemoteViews(contexto.packageName, R.layout.widget_item)
                    novelaView.setTextViewText(R.id.txtTitulo, titulo)
                    novelaView.setTextViewText(R.id.txtAutor, autor)

                    views.addView(R.id.widgetFavo, novelaView)
                Fin Para

                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
            .addOnFailureListener { excepcion ->
                Log.w("FavoritasWidget", "Error al obtener las favoritas", excepcion)
            }
    Fin Método

Fin Clase

```

En esta widgte, la cual el usuario debe añadir al dispositivo para poder verla, podra ver el título y el autor de las novelas favoritas.



## Proceso de desarrollo

Para realizar la aplicacion se ha hecho uso del anterior proyecto como base y este ha sido modificado.

En el proyecto se nos solicitaba que nuestra aplicación de gestor de novelas contase con una base de datos que guardara usuarios, por lo tanto los cambios han sido: 

 -> Lo primero será modificar la clase principal para poder cambiar de fragmento y que uno de ellos tenga toda la logica de negocio. 

 -> Añadir un Widget al dispositivo para poder ver su correcto funcionamiento. 
